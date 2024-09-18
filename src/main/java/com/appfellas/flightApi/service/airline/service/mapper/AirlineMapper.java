package com.appfellas.flightApi.service.airline.service.mapper;

import com.appfellas.flightApi.core.dao.mapper.BaseMapper;
import com.appfellas.flightApi.service.airline.dto.input.AirlineInput;
import com.appfellas.flightApi.service.airline.entity.Airline;
import com.appfellas.flightApi.service.airport.service.mapper.AddressMapper;
import com.appfellas.flightApi.service.flight.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AirlineMapper implements BaseMapper<Airline, AirlineInput> {

    private final FlightService flightService;
    private final AddressMapper addressMapper;

    @Autowired
    public AirlineMapper(FlightService flightService, AddressMapper addressMapper) {
        this.flightService = flightService;
        this.addressMapper = addressMapper;
    }

    @Override
    public Airline createEntity(AirlineInput input) {
        Airline airline = updateEntity(new Airline(), input);
        airline.setName(input.getName());
        airline.setAirlineCode(input.getAirlineCode());
        airline.setAirlineIATACode(input.getAirlineIATACode());
        airline.setCountry(addressMapper.createEntity(input.getCountry()));
        return airline;
    }

    @Override
    public Airline updateEntity(Airline entity, AirlineInput input) {
        if (!input.getFlightIds().isEmpty()) {
            entity.getFlightIds().addAll(input.getFlightIds().stream().map(new Function<String, String>() {
                @Override
                public String apply(String s) {
                    return null; // TODO : find the flight with the given id and set flight to airline / airline to flight!
                }
            }).toList());
        }
        return null;
    }
}
