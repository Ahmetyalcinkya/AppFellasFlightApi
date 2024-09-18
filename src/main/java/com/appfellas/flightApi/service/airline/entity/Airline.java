package com.appfellas.flightApi.service.airline.entity;

import com.appfellas.flightApi.service.airport.entity.embeddable.Address;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "airline")
public class Airline {

    @Id
    private String id;
    private String name;
    private Integer airlineCode;
    private String airlineIATACode;
    private Address country;
    private Set<String> flightIds = new HashSet<>();

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

    public Set<String> getFlightIds() {
        return flightIds;
    }

    public void setFlightIds(Set<String> flightIds) {
        this.flightIds = flightIds;
    }
}
