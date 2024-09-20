package com.appfellas.flightApi.service.user.dto.response;

import com.appfellas.flightApi.service.flight.dto.response.FlightResponse;

import java.util.List;

public class UserResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private List<FlightResponse> flights;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<FlightResponse> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightResponse> flights) {
        this.flights = flights;
    }
}
