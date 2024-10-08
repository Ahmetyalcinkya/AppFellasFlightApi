package com.appfellas.flightApi.service.flight.entity;

import com.appfellas.flightApi.core.enums.FlightDirection;
import com.appfellas.flightApi.service.airline.entity.Airline;
import com.appfellas.flightApi.service.flight.entity.embeddable.AirCraftType;
import com.appfellas.flightApi.service.flight.entity.embeddable.FlightRoute;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "flight")
public class Flight {

    @Id
    private String id;
    private LocalDateTime lastUpdatedAt;
    private LocalDateTime actualLandingTime;
    private AirCraftType airCraftType;
    private LocalDateTime estimatedLandingTime;
    private LocalDateTime expectedTimeOnBelt;
    private FlightDirection flightDirection;
    private String flightName;
    private Integer flightNumber;
    private Boolean isOperationalFlight;
    private LocalDateTime scheduledDateTime;
    private LocalDate scheduleDate;
    private LocalTime scheduleTime;
    private Set<String> passengers = new HashSet<>();
    private Integer capacity;
    private Integer terminal;
    @DBRef
    private Airline airline;
    private FlightRoute route;
    private Long price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public LocalDateTime getActualLandingTime() {
        return actualLandingTime;
    }

    public void setActualLandingTime(LocalDateTime actualLandingTime) {
        this.actualLandingTime = actualLandingTime;
    }

    public AirCraftType getAirCraftType() {
        return airCraftType;
    }

    public void setAirCraftType(AirCraftType airCraftType) {
        this.airCraftType = airCraftType;
    }

    public LocalDateTime getEstimatedLandingTime() {
        return estimatedLandingTime;
    }

    public void setEstimatedLandingTime(LocalDateTime estimatedLandingTime) {
        this.estimatedLandingTime = estimatedLandingTime;
    }

    public LocalDateTime getExpectedTimeOnBelt() {
        return expectedTimeOnBelt;
    }

    public void setExpectedTimeOnBelt(LocalDateTime expectedTimeOnBelt) {
        this.expectedTimeOnBelt = expectedTimeOnBelt;
    }

    public FlightDirection getFlightDirection() {
        return flightDirection;
    }

    public void setFlightDirection(FlightDirection flightDirection) {
        this.flightDirection = flightDirection;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public Integer getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Integer flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Boolean getOperationalFlight() {
        return isOperationalFlight;
    }

    public void setOperationalFlight(Boolean operationalFlight) {
        isOperationalFlight = operationalFlight;
    }

    public LocalDateTime getScheduledDateTime() {
        return scheduledDateTime;
    }

    public void setScheduledDateTime(LocalDateTime scheduledDateTime) {
        this.scheduledDateTime = scheduledDateTime;
    }

    public LocalDate getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public LocalTime getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(LocalTime scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public Set<String> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<String> passengers) {
        this.passengers = passengers;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getTerminal() {
        return terminal;
    }

    public void setTerminal(Integer terminal) {
        this.terminal = terminal;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public FlightRoute getRoute() {
        return route;
    }

    public void setRoute(FlightRoute route) {
        this.route = route;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
