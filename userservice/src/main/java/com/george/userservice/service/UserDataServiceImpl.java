package com.george.userservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.george.userservice.dto.UserDataMapper;
import com.george.userservice.dto.UserDataRequest;
import com.george.userservice.dto.UserDataResponse;
import com.george.userservice.exception.NotFoundException;
import com.george.userservice.model.Locality;
import com.george.userservice.model.UserData;
import com.george.userservice.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class UserDataServiceImpl implements UserDataService {

    private UserDataRepository userDataRepository;
    private UserDataMapper userDataMapper;
    private ObjectMapper jacksonObjectMapper;

    @Autowired
    public UserDataServiceImpl(UserDataRepository userDataRepository, UserDataMapper userDataMapper, ObjectMapper jacksonObjectMapper) {
        this.userDataRepository = userDataRepository;
        this.userDataMapper = userDataMapper;
        this.jacksonObjectMapper = jacksonObjectMapper;
    }

    @Override
    public List<UserDataResponse> findAll() {
        return userDataRepository.findAll().stream()
                .map(userData -> userDataMapper.toDto(userData))
                .collect(Collectors.toList());
    }

    @Override
    public UserDataResponse findById(Long id) {
        UserData userData = userDataRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Data of user not found!"));

        return userDataMapper.toDto(userData);
    }

    @Override
    public UserDataResponse save(UserDataRequest createUserDataRequest) {
        UserData userData = userDataMapper.toEntity(createUserDataRequest);
        UserData newUserData = userDataRepository.save(userData);
        return userDataMapper.toDto(newUserData);
    }

    @Override
    public UserDataResponse update(Long id, UserDataRequest updateDataRequest) {
        UserData userDataToUpdate = userDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data of user not found!"));

        userDataMapper.toEntity(userDataToUpdate, updateDataRequest);
        UserData updatedUserData = userDataRepository.save(userDataToUpdate);
        return userDataMapper.toDto(updatedUserData);

    }

    @Override
    public void delete(Long id) {
        userDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data of user not found!"));
        userDataRepository.deleteById(id);
    }

    @Override
    public List<UserDataResponse> findByLocality(Locality locality) {
        return userDataRepository.findAll()
                .stream()
                .filter(userData -> userData.getLocality().equals(locality))
                .map(userDataMapper::toDto)
                .collect(Collectors.toList());
    }
}
