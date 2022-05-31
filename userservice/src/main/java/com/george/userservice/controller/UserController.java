package com.george.userservice.controller;

import com.george.userservice.filter.CustomAuthenticationFilter;
import com.george.userservice.model.AuthenticationRequest;
import com.george.userservice.model.AuthenticationResponse;
import com.george.userservice.model.UserDTO;
import com.george.userservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private  UserServiceImpl userService;
    @Autowired
    private  CustomAuthenticationFilter customAuthenticationFilter;
    @Autowired
    private  AuthenticationManager authenticationManager;


    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Entex App!";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = customAuthenticationFilter.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

