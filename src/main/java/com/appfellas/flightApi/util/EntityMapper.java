package com.appfellas.flightApi.util;

import com.appfellas.flightApi.service.airline.dto.response.AirlineResponse;
import com.appfellas.flightApi.service.airline.entity.Airline;
import com.appfellas.flightApi.service.airport.dto.response.AirportResponse;
import com.appfellas.flightApi.service.airport.entity.Airport;
import com.appfellas.flightApi.service.flight.dto.response.FlightResponse;
import com.appfellas.flightApi.service.flight.entity.Flight;
import com.appfellas.flightApi.service.user.dto.response.UserResponse;
import com.appfellas.flightApi.service.user.entity.User;

import java.util.function.Function;

public class EntityMapper {

    public static UserResponse user(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setRole(user.getRole().name());
        userResponse.setFlights(user.getFlights().stream().map(EntityMapper::flight).toList());
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

    public static AirlineResponse airline(Airline airline) {
        AirlineResponse airlineResponse = new AirlineResponse();
        airlineResponse.setId(airline.getId());
        airlineResponse.setAirlineCode(airline.getAirlineCode());
        airlineResponse.setAirlineIATACode(airline.getAirlineIATACode());
        airlineResponse.setName(airline.getName());
        airlineResponse.setCountry(airline.getCountry());
        return airlineResponse;
    }

    public static FlightResponse flight(Flight flight) {
        FlightResponse flightResponse = new FlightResponse();
        flightResponse.setId(flight.getId());
        flightResponse.setLastUpdatedAt(flight.getLastUpdatedAt());
        flightResponse.setActualLandingTime(flight.getActualLandingTime());
        flightResponse.setEstimatedLandingTime(flight.getEstimatedLandingTime());
        flightResponse.setExpectedTimeOnBelt(flight.getExpectedTimeOnBelt());
        flightResponse.setFlightDirection(flight.getFlightDirection());
        flightResponse.setFlightName(flightResponse.getFlightName());
        flightResponse.setFlightNumber(flightResponse.getFlightNumber());
        flightResponse.setOperationalFlight(flightResponse.getOperationalFlight());
        flightResponse.setScheduledDateTime(flight.getScheduledDateTime());
        flightResponse.setScheduleDate(flight.getScheduleDate());
        flightResponse.setScheduleTime(flight.getScheduleTime());
        flightResponse.setCapacity(flight.getCapacity() - flight.getPassengers().size());
        flightResponse.setTerminal(flight.getTerminal());
        flightResponse.setAirlineName(flight.getAirline().getName());
        flightResponse.setRoute(flight.getRoute());
        flightResponse.setPrice(flight.getPrice());
        flightResponse.setPassengerIds(flight.getPassengers());
        return flightResponse;
    }
}
