package com.appfellas.flightApi.service.airline.dto.input;

import com.appfellas.flightApi.service.airport.dto.input.AddressInput;

import java.util.List;

public class AirlineInput {

    private String name;
    private Integer airlineCode;
    private String airlineIATACode;
    private AddressInput country;
    private List<String> flightIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(Integer airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getAirlineIATACode() {
        return airlineIATACode;
    }

    public void setAirlineIATACode(String airlineIATACode) {
        this.airlineIATACode = airlineIATACode;
    }

    public AddressInput getCountry() {
        return country;
    }

    public void setCountry(AddressInput country) {
        this.country = country;
    }

    public List<String> getFlightIds() {
        return flightIds;
    }

    public void setFlightIds(List<String> flightIds) {
        this.flightIds = flightIds;
    }
}
