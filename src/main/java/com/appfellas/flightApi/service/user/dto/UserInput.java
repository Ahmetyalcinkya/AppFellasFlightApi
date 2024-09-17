package com.appfellas.flightApi.service.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UserInput {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

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
}
