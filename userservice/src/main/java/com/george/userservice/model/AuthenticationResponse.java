package com.george.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

//This is class is required for creating a response containing the JWT to be returned to the user.

@Data
@AllArgsConstructor
public class AuthenticationResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwtToken;
}
