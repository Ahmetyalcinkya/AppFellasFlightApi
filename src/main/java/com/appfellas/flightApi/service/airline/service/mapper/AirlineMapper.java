package com.appfellas.flightApi.service.airline.service.mapper;

import com.appfellas.flightApi.core.dao.mapper.BaseCreateMapper;
import com.appfellas.flightApi.service.airline.dto.input.AirlineInput;
import com.appfellas.flightApi.service.airline.entity.Airline;
import com.appfellas.flightApi.service.airport.service.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AirlineMapper implements BaseCreateMapper<Airline, AirlineInput> {

    private final AddressMapper addressMapper;

    @Autowired
    public AirlineMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Override
    public Airline createEntity(AirlineInput input) {
        Airline airline = new Airline();
        airline.setName(input.getName());
        airline.setAirlineCode(input.getAirlineCode());
        airline.setAirlineIATACode(input.getAirlineIATACode());
        airline.setCountry(addressMapper.createEntity(input.getCountry()));
        return airline;
    }
}
