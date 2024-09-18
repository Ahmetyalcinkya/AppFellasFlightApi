package com.appfellas.flightApi.util;

import com.appfellas.flightApi.service.airport.dto.response.AirportResponse;
import com.appfellas.flightApi.service.airport.entity.Airport;
import com.appfellas.flightApi.service.user.dto.response.UserResponse;
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

    public static AirportResponse airport(Airport airport) {
        AirportResponse airportResponse = new AirportResponse();
        airportResponse.setId(airport.getId());
        airportResponse.setName(airport.getName());
        airportResponse.setIATACode(airport.getIATACode());
        airportResponse.setAddress(airport.getAddress());
        return airportResponse;
    }
}
