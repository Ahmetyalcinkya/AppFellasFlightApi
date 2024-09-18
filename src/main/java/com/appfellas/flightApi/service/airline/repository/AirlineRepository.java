package com.appfellas.flightApi.service.airline.repository;

import com.appfellas.flightApi.service.airline.entity.Airline;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends MongoRepository<Airline, String> {
}
