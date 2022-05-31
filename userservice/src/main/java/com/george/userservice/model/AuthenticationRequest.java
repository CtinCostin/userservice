package com.george.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String name;
    private String username;
    private String password;
}
