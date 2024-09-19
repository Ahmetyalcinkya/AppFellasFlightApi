package com.appfellas.flightApi.core.exception;

import org.springframework.http.HttpStatus;

public class FlightApiException extends RuntimeException {

    private HttpStatus status;

    public FlightApiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
