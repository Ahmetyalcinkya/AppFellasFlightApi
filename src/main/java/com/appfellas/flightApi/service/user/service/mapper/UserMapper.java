package com.appfellas.flightApi.service.user.service.mapper;

import com.appfellas.flightApi.core.dao.mapper.BaseMapper;
import com.appfellas.flightApi.core.enums.Role;
import com.appfellas.flightApi.service.flight.entity.Flight;
import com.appfellas.flightApi.service.flight.repository.FlightRepository;
import com.appfellas.flightApi.service.flight.service.FlightService;
import com.appfellas.flightApi.service.user.dto.input.UserInput;
import com.appfellas.flightApi.service.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper implements BaseMapper<User, UserInput> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMapper.class);

    //TODO : Password encoder will be autowired
    private final FlightService flightService;
    private final FlightRepository flightRepository;

    @Autowired
    public UserMapper(FlightService flightService, FlightRepository flightRepository) {
        this.flightService = flightService;
        this.flightRepository = flightRepository;
    }

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
        if (!input.getFlights().isEmpty()) {
            entity.getFlights().addAll(input.getFlights().stream().map(id -> {
                Flight flight = flightService.findById(id);
                if (flight.getPassengers().contains(entity.getId())) LOGGER.warn("The user with the id: {}, has already in the flight with the id: {}", entity.getId(), flight.getId());
                if (flight.getCapacity() - flight.getPassengers().size() > 0) {
                    flight.getPassengers().add(entity.getId());
                } else {
                    LOGGER.warn("The flight capacity is full");
                }
                flightRepository.save(flight);
                return flight;
            }).toList());
        }
        return entity;
    }
}
