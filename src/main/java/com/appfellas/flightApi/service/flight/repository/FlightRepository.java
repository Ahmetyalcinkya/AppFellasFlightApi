package com.appfellas.flightApi.service.flight.repository;

import com.appfellas.flightApi.service.flight.entity.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends MongoRepository<Flight, String> {
}
