package com.appfellas.flightApi.service.airport.dto.response;

import com.appfellas.flightApi.service.airport.entity.embeddable.Address;

public class AirportResponse {

    private String id;
    private String name;
    private String IATACode;
    private Address address;

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

    public String getIATACode() {
        return IATACode;
    }

    public void setIATACode(String IATACode) {
        this.IATACode = IATACode;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
