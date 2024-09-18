package com.appfellas.flightApi.service.airline.service;

import com.appfellas.flightApi.service.airline.dto.input.AirlineInput;
import com.appfellas.flightApi.service.airline.entity.Airline;
import com.appfellas.flightApi.service.airline.repository.AirlineRepository;
import com.appfellas.flightApi.service.airline.service.mapper.AirlineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineService {

    private final AirlineRepository airlineRepository;
    private final AirlineMapper airlineMapper;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public AirlineService(AirlineRepository airlineRepository, AirlineMapper airlineMapper, MongoTemplate mongoTemplate) {
        this.airlineRepository = airlineRepository;
        this.airlineMapper = airlineMapper;
        this.mongoTemplate = mongoTemplate;
    }

    public List<Airline> findAll(){
        return airlineRepository.findAll();
    }

    public Airline findById(String id) {
        return airlineRepository.findById(id).orElseThrow(() -> new RuntimeException("Airline not found!"));
    }

    public Airline findByIATACode(String IATACode) {
        return mongoTemplate.findOne(Query.query(Criteria.where("airlineIATACode").is(IATACode)), Airline.class);
    }

    public void save(AirlineInput input) {
        airlineRepository.save(airlineMapper.createEntity(input));
    }

    public void delete(String id) {
        airlineRepository.delete(findById(id));
    }
}
