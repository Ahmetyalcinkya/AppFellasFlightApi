package com.appfellas.flightApi.service.flight.service;

import com.appfellas.flightApi.core.enums.SortDirection;
import com.appfellas.flightApi.core.enums.SortProperty;
import com.appfellas.flightApi.service.flight.dto.input.FlightInput;
import com.appfellas.flightApi.service.flight.entity.Flight;
import com.appfellas.flightApi.service.flight.repository.FlightRepository;
import com.appfellas.flightApi.service.flight.service.mapper.FlightMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public FlightService(FlightRepository flightRepository, FlightMapper flightMapper, MongoTemplate mongoTemplate) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
        this.mongoTemplate = mongoTemplate;
    }

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    public Flight findById(String id) {
        return flightRepository.findById(id).orElseThrow(() -> new RuntimeException("Flight not found!"));
    }

    public Flight findByFlightName(String name) {
        return mongoTemplate.findOne(Query.query(Criteria.where("flightName").is(name)), Flight.class);
    }

    public List<Flight> findAllFlightByIds(List<String> ids) {
        return flightRepository.findAllById(ids);
    }

    public List<Flight> filterFlightByDate(LocalDate startDate, LocalDate endDate) {
        return mongoTemplate.find(Query.query(Criteria.where("scheduledDateTime")
                .gte(LocalDateTime.of(startDate, LocalTime.MIN))
                .lte(LocalDateTime.of(endDate, LocalTime.MAX)).and("isOperationalFlight").is(false)), Flight.class);
    }

    public List<Flight> filterFlightByTime(LocalTime startTime, LocalTime endTime) {
        return mongoTemplate.find(Query.query(Criteria.where("scheduledDateTime")
                .gte(LocalDateTime.of(LocalDate.now(), startTime))
                .lte(LocalDateTime.of(LocalDate.now(), endTime)).and("isOperationalFlight").is(false)), Flight.class);
    }

    public List<Flight> filterFlightByAirline(String airlineId) {
        return mongoTemplate.find(Query.query(Criteria.where("airline.id").is(airlineId).and("isOperationalFlight").is(false)), Flight.class);
    }

    public List<Flight> filterFlight(String startDate, String endDate, String startTime, String endTime, String airlineId, SortProperty sortProperty, SortDirection sortDirection, String departureAirportIATA ,String arrivalAirportIATA) {
        Criteria criteria = new Criteria();
        if (!StringUtils.isAllBlank(startDate, endDate)) {
            LocalDate startingDate = LocalDate.parse(startDate);
            LocalDate endingDate = LocalDate.parse(endDate);
            if (startingDate.isAfter(endingDate)) {
                throw new RuntimeException("End date cannot smaller than start date");
            }
            if (!StringUtils.isAllBlank(startTime, endTime)) {
                LocalTime startingTime = LocalTime.parse(startTime);
                LocalTime endingTime = LocalTime.parse(endTime);
                if (startingTime.isAfter(endingTime)) {
                    throw new RuntimeException("End time cannot smaller than start time");
                }
                criteria = criteria.and("scheduledDateTime")
                        .gte(LocalDateTime.of(startingDate, startingTime))
                        .lte(LocalDateTime.of(endingDate, endingTime));
            } else {
                criteria = criteria.and("scheduledDateTime")
                        .gte(LocalDateTime.of(startingDate, LocalTime.MIN))
                        .lte(LocalDateTime.of(endingDate, LocalTime.MAX));
            }
        } else {
            if (!StringUtils.isAllBlank(startTime, endTime)) {
                LocalTime startingTime = LocalTime.parse(startTime);
                LocalTime endingTime = LocalTime.parse(endTime);
                if (startingTime.isAfter(endingTime)) {
                    throw new RuntimeException("End time cannot smaller than start time");
                }
                criteria = criteria.and("scheduledDateTime")
                        .gte(LocalDateTime.of(LocalDate.now(), startingTime))
                        .lte(LocalDateTime.of(LocalDate.now(), endingTime));
            }
        }
        if (airlineId != null && !airlineId.isEmpty()) {
            criteria = criteria.and("airline.id").is(airlineId);
        }
        if (!StringUtils.isAllBlank(departureAirportIATA, arrivalAirportIATA)) {
            criteria = criteria.and("route.departureIATACode").is(departureAirportIATA).and("route.arrivalIATACode").is(arrivalAirportIATA);
        } else if (StringUtils.isNotBlank(departureAirportIATA) && StringUtils.isBlank(arrivalAirportIATA)) {
            criteria = criteria.and("route.departureIATACode").is(departureAirportIATA);
        } else if (StringUtils.isBlank(departureAirportIATA) && StringUtils.isNotBlank(arrivalAirportIATA)) {
            criteria = criteria.and("route.arrivalIATACode").is(arrivalAirportIATA);
        }
        if (sortDirection != null) {
            Sort.Direction direction = Sort.Direction.valueOf(sortDirection.name());
            Sort sort = Sort.by(direction, sortProperty == null ? "scheduledDateTime" : "price");
            return mongoTemplate.find(Query.query(criteria.and("isOperationalFlight").is(false)).with(sort), Flight.class);
        }
        return mongoTemplate.find(Query.query(criteria.and("isOperationalFlight").is(false)), Flight.class);
    }

    public void save(FlightInput input) {
        flightRepository.save(flightMapper.createEntity(input));
    }

    public void update(String id, FlightInput input) {
        flightRepository.save(flightMapper.updateEntity(findById(id), input));
    }

    public void delete(String id) {
        flightRepository.delete(findById(id));
    }
}
