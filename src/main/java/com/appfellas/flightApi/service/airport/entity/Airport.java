package com.appfellas.flightApi.service.airport.entity;

import com.appfellas.flightApi.service.airport.entity.embeddable.Address;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "airport")
public class Airport {

    @Id
    private String id;
    private String name;
    private String IATACode;
    private Address address;
    private Integer terminal;

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

    public Integer getTerminal() {
        return terminal;
    }

    public void setTerminal(Integer terminal) {
        this.terminal = terminal;
    }
}
