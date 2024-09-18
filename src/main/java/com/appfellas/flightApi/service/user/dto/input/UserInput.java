package com.appfellas.flightApi.service.user.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.util.List;

public class UserInput {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<String> flights;

    @Size(min = 3, max = 255)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Size(min = 3, max = 255)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Email
    @Size(max = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Size(min = 6, max = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getFlights() {
        return flights;
    }

    public void setFlights(List<String> flights) {
        this.flights = flights;
    }
}
