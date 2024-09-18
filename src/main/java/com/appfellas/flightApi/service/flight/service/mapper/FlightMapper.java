package com.appfellas.flightApi.service.flight.service.mapper;

import com.appfellas.flightApi.core.dao.mapper.BaseMapper;
import com.appfellas.flightApi.core.enums.FlightDirection;
import com.appfellas.flightApi.service.airline.entity.Airline;
import com.appfellas.flightApi.service.airline.repository.AirlineRepository;
import com.appfellas.flightApi.service.airline.service.AirlineService;
import com.appfellas.flightApi.service.flight.dto.input.FlightInput;
import com.appfellas.flightApi.service.flight.entity.Flight;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper implements BaseMapper<Flight, FlightInput> {

    private final AirCraftTypeMapper airCraftTypeMapper;
    private final FlightRouteMapper flightRouteMapper;
    private final AirlineService airlineService;
    private final AirlineRepository airlineRepository;

    public FlightMapper(AirCraftTypeMapper airCraftTypeMapper, FlightRouteMapper flightRouteMapper, AirlineService airlineService, AirlineRepository airlineRepository) {
        this.airCraftTypeMapper = airCraftTypeMapper;
        this.flightRouteMapper = flightRouteMapper;
        this.airlineService = airlineService;
        this.airlineRepository = airlineRepository;
    }

    @Override
    public Flight createEntity(FlightInput input) {
        Flight flight = updateEntity(new Flight(), input);
        flight.setCapacity(329);
        flight.setFlightDirection(FlightDirection.valueOf(input.getFlightDirection()));
        flight.setFlightNumber(input.getFlightNumber());
        flight.setOperationalFlight(input.getOperationalFlight());
        flight.setAirCraftType(airCraftTypeMapper.createEntity(input.getAirCraftType()));
        Airline airline = airlineService.findByIATACode(input.getPrefixIATA());
        airline.getFlightIds().add(flight.getId());
        flight.setAirline(airline);
        airlineRepository.save(airline);
        flight.setRoute(flightRouteMapper.createEntity(input.getRoute()));
        flight.setPrice(randomPrice());
        return flight;
    }

    @Override
    public Flight updateEntity(Flight entity, FlightInput input) {
        entity.setLastUpdatedAt(input.getLastUpdatedAt());
        entity.setActualLandingTime(input.getActualLandingTime());
        entity.setEstimatedLandingTime(input.getEstimatedLandingTime());
        entity.setExpectedTimeOnBelt(input.getExpectedTimeOnBelt());
        entity.setScheduledDateTime(input.getScheduledDateTime());
        entity.setScheduleDate(input.getScheduleDate());
        entity.setScheduleTime(input.getScheduleTime());
        return entity;
    }

    private Long randomPrice() {
        return Math.round(Math.random() * 1000);
    }
}
