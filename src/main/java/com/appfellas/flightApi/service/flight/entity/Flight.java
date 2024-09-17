package com.appfellas.flightApi.service.flight.entity;

import com.appfellas.flightApi.core.enums.FlightDirection;
import com.appfellas.flightApi.service.airline.entity.Airline;
import com.appfellas.flightApi.service.airport.entity.Airport;
import com.appfellas.flightApi.service.flight.entity.embeddable.AirCraftType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private Integer flightNumber;
    private Boolean isOperationalFlight;
    private String mainFlight;
    private Integer airlineCode;
    private LocalDateTime scheduledDateTime;
    private LocalDate scheduleDate;
    private LocalTime scheduleTime;
    private Integer terminal;
    private Set<String> passengers; // TODO Capacity - UserIdCount = 0 ? dolu : boş;
    private Integer capacity;
    @DBRef
    private Airport airport;
    @DBRef
    private Airline airline;

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

    public String getMainFlight() {
        return mainFlight;
    }

    public void setMainFlight(String mainFlight) {
        this.mainFlight = mainFlight;
    }

    public Integer getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(Integer airlineCode) {
        this.airlineCode = airlineCode;
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

    public Integer getTerminal() {
        return terminal;
    }

    public void setTerminal(Integer terminal) {
        this.terminal = terminal;
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

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }
}
