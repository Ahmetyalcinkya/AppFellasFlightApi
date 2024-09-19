package com.appfellas.flightApi.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDetails> handleException(FlightApiException e, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(e.getMessage());
        errorDetails.setError(request.getDescription(false));
        errorDetails.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorDetails> handleException(Exception e, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(e.getMessage());
        errorDetails.setError(request.getDescription(false));
        errorDetails.setTimestamp(LocalDateTime.now());

    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
