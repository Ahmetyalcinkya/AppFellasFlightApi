package com.appfellas.flightApi.service.airline.dto.response;

import com.appfellas.flightApi.service.airport.entity.embeddable.Address;

public class AirlineResponse {

    private String id;
    private String name;
    private Integer airlineCode;
    private String airlineIATACode;
    private Address country;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Address getCountry() {
        return country;
    }

    public void setCountry(Address country) {
        this.country = country;
    }
}
