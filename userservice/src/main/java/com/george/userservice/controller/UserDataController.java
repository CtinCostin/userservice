package com.george.userservice.controller;

import com.george.userservice.model.UserData;
import com.george.userservice.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserDataController {

    private UserDataRepository userDataRepository;

    @Autowired
    public UserDataController(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @GetMapping("/")
    public List<UserData> getAllData() {

        List<UserData> listOfData = new ArrayList<>();
        Iterable<UserData> dataIterable = userDataRepository.findAll();

        dataIterable.forEach(listOfData::add);
        return listOfData;
    }

    @PostMapping("/")
    public UserData createData(@Valid @RequestBody UserData userData) {

        return userDataRepository.save(userData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserData> getData(@PathVariable("id") Long id) {
        Optional<UserData> userData1 = userDataRepository.findById(id);
        return userData1
                .map(userData -> new ResponseEntity<>(userData, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserData> updateData(@PathVariable("id") Long id, @RequestBody UserData userData) {

        Optional<UserData> userData1 = userDataRepository.findById(id);
        if (userData1.isPresent()) {
            UserData savedData = userData1.get();
            savedData.setName(userData.getName());
            savedData.setEmail(userData.getEmail());
            savedData.setLocality(userData.getLocality());
            savedData.setCounty(userData.getCounty());

            UserData updatedData = userDataRepository.save(savedData);
            return new ResponseEntity<>(updatedData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteData(@PathVariable("id") Long id) {

        try {
            userDataRepository.deleteById(id);
        } catch (Exception exception) {
            return new ResponseEntity<>("Fail to delete!", HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>("User data deleted!", HttpStatus.OK);
    }

}
