package com.appfellas.flightApi.service.airport.service.mapper;

import com.appfellas.flightApi.core.dao.mapper.BaseMapper;
import com.appfellas.flightApi.service.airport.dto.input.AirportInput;
import com.appfellas.flightApi.service.airport.entity.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AirportMapper implements BaseMapper<Airport, AirportInput> {

    private final AddressMapper addressMapper;

    @Autowired
    public AirportMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Override
    public Airport createEntity(AirportInput input) {
        return updateEntity(new Airport(), input);
    }

    @Override
    public Airport updateEntity(Airport entity, AirportInput input) {
        if (input.getName() != null) entity.setName(input.getName());
        if (input.getIATACode() != null) entity.setIATACode(input.getIATACode());
        if (input.getAddress() != null) entity.setAddress(addressMapper.createEntity(input.getAddress()));
        return entity;
    }
}
