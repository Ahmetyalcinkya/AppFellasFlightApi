package com.appfellas.flightApi.service.flight.service.mapper;

import com.appfellas.flightApi.core.dao.mapper.BaseMapper;
import com.appfellas.flightApi.core.enums.FlightDirection;
import com.appfellas.flightApi.service.flight.dto.input.FlightInput;
import com.appfellas.flightApi.service.flight.entity.Flight;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class FlightMapper implements BaseMapper<Flight, FlightInput> {

    // TODO : Airlines and airport sevice will be autowired here!
    private final AirCraftTypeMapper airCraftTypeMapper;
    private final FlightRouteMapper flightRouteMapper;

    public FlightMapper(AirCraftTypeMapper airCraftTypeMapper, FlightRouteMapper flightRouteMapper) {
        this.airCraftTypeMapper = airCraftTypeMapper;
        this.flightRouteMapper = flightRouteMapper;
    }

    @Override
    public Flight createEntity(FlightInput input) {
        Flight flight = updateEntity(new Flight(), input);
        flight.setCapacity(329);
        flight.setFlightDirection(FlightDirection.valueOf(input.getFlightDirection()));
        flight.setFlightNumber(input.getFlightNumber());
        flight.setOperationalFlight(input.getOperationalFlight());
        flight.setAirCraftType(airCraftTypeMapper.createEntity(input.getAirCraftType()));
//        flight.setAirport();
//        flight.setAirline();
        flight.setRoute(flightRouteMapper.createEntity(input.getRoute()));
        return flight;
    }

    @Override
    public Flight updateEntity(Flight entity, FlightInput input) {
        entity.setLastUpdatedAt(input.getLastUpdateAt());
        entity.setActualLandingTime(input.getActualLandingTime());
        entity.setEstimatedLandingTime(input.getEstimatedLandingTime());
        entity.setExpectedTimeOnBelt(input.getExpectedTimeOnBelt());
        entity.setScheduledDateTime(input.getScheduledDateTime());
        entity.setScheduleDate(input.getScheduleDate());
        entity.setScheduleTime(input.getScheduleTime());
        return entity;
    }

    private Integer randomIndex(){
        Random random = new Random();
        return random.nextInt(11);
    }
}
