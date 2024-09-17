package com.appfellas.flightApi.util;

import com.appfellas.flightApi.service.user.dto.UserResponse;
import com.appfellas.flightApi.service.user.entity.User;

public class EntityMapper {

    public static UserResponse user(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setRole(user.getRole().name());
        return userResponse;
    }
}
