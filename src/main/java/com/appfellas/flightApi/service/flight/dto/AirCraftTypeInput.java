package com.appfellas.flightApi.service.flight.dto;

public class AirCraftTypeInput {

    private String iataMain;
    private String iataSub;

    public String getIataMain() {
        return iataMain;
    }

    public void setIataMain(String iataMain) {
        this.iataMain = iataMain;
    }

    public String getIataSub() {
        return iataSub;
    }

    public void setIataSub(String iataSub) {
        this.iataSub = iataSub;
    }
}
