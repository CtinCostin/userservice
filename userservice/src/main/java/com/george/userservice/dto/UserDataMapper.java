package com.george.userservice.dto;

import com.george.userservice.model.UserData;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

@Component
public class UserDataMapper {

    public UserDataResponse toDto(UserData userData) {
        UserDataResponse dto = new UserDataResponse();
        dto.setId(userData.getId());
        dto.setName(userData.getName());
        dto.setEmail(userData.getEmail());
        dto.setCounty(userData.getCounty());
        dto.setLocality(userData.getLocality());
        return dto;
    }

    public UserData toEntity(UserDataRequest userDataRequest) {
        UserData userData = new UserData();
        userData.setName(userDataRequest.getName());
        userData.setEmail(userDataRequest.getEmail());
        userData.setCounty(userDataRequest.getCounty());
        userData.setLocality(userDataRequest.getLocality());
        return userData;
    }

    public UserData toEntity(UserData userDataToUpdate, UserDataRequest updateRequest) {
        if (!StringUtils.isEmpty(updateRequest.getName())) {
            userDataToUpdate.setName(updateRequest.getName());
        }

        if (!StringUtils.isEmpty(updateRequest.getEmail())) {
            userDataToUpdate.setEmail(updateRequest.getEmail());
        }

        if (updateRequest.getCounty() != null) {
            userDataToUpdate.setCounty(updateRequest.getCounty());
        }
        if (updateRequest.getLocality() != null) {
            userDataToUpdate.setLocality(updateRequest.getLocality());
        }
        return userDataToUpdate;
    }

}
