package com.appfellas.flightApi.service.airline.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "airline")
public class Airline {

    @Id
    private String id;
    private Integer airlineCode;
    private String airlineIATACode; // TODO: FindByAirlineIATACode;
}
