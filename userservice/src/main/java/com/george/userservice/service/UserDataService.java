package com.george.userservice.service;

import com.george.userservice.dto.UserDataRequest;
import com.george.userservice.dto.UserDataResponse;
import com.george.userservice.model.Locality;

import java.util.List;

public interface UserDataService {

    List<UserDataResponse> findAll();

    UserDataResponse findById(Long id);

    UserDataResponse save(UserDataRequest createUserDataRequest);

    UserDataResponse update(Long id, UserDataRequest updateRequest);

    void delete(Long id);

    List<UserDataResponse> findByLocality(Locality locality);



}
