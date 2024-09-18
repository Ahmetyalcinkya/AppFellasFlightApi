package com.appfellas.flightApi.service.airport.repository;

import com.appfellas.flightApi.service.airport.entity.Airport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends MongoRepository<Airport, String> {
}
