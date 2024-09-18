package com.appfellas.flightApi.service.airport.service;

import com.appfellas.flightApi.service.airport.dto.input.AirportInput;
import com.appfellas.flightApi.service.airport.entity.Airport;
import com.appfellas.flightApi.service.airport.repository.AirportRepository;
import com.appfellas.flightApi.service.airport.service.mapper.AirportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {

    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public AirportService(AirportRepository airportRepository, AirportMapper airportMapper, MongoTemplate mongoTemplate) {
        this.airportRepository = airportRepository;
        this.airportMapper = airportMapper;
        this.mongoTemplate = mongoTemplate;
    }

    public List<Airport> findAll(){
        return airportRepository.findAll();
    }

    public Airport findById(String id){
        return airportRepository.findById(id).orElseThrow(() -> new RuntimeException("Airport not found!"));
    }

    public Airport findBYIATACode(String IATACode) {
        return mongoTemplate.findOne(Query.query(Criteria.where("IATACode").is(IATACode)), Airport.class);
    }

    public void save(AirportInput input){
        airportRepository.save(airportMapper.createEntity(input));
    }

    public void update(String id, AirportInput input) {
        airportRepository.save(airportMapper.updateEntity(findById(id), input));
    }

    public void delete(String id) {
        airportRepository.delete(findById(id));
    }
}
