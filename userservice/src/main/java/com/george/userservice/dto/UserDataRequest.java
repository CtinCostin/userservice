package com.george.userservice.dto;

import com.george.userservice.model.County;
import com.george.userservice.model.Locality;
import lombok.Data;

@Data
public class UserDataRequest {

    private String name;
    private String email;
    private County county;
    private Locality locality;
}
