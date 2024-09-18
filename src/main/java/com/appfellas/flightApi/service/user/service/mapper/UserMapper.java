package com.appfellas.flightApi.service.user.service.mapper;

import com.appfellas.flightApi.core.dao.mapper.BaseMapper;
import com.appfellas.flightApi.core.enums.Role;
import com.appfellas.flightApi.service.user.dto.input.UserInput;
import com.appfellas.flightApi.service.user.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper implements BaseMapper<User, UserInput> {

    //TODO : Password encoder will be autowired
    @Override
    public User createEntity(UserInput input) {
        User entity = updateEntity(new User(), input);
        entity.setRole(Role.USER);
        entity.setCreatedDateTime(LocalDateTime.now());
//        entity.setPassword();
        return entity;
    }

    @Override
    public User updateEntity(User entity, UserInput input) {
        if (input.getFirstName() != null) entity.setFirstName(input.getFirstName());
        if (input.getLastName() != null) entity.setLastName(input.getLastName());
        if (input.getEmail() != null) entity.setEmail(input.getEmail());
        return entity;
    }
}
