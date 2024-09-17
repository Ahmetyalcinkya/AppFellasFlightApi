package com.appfellas.flightApi.core.enums;

public enum FlightDirection {
    A("Arrivals"), D("Departures");

    private final String value;

    FlightDirection(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
