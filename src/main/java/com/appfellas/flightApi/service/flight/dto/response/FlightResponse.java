package com.appfellas.flightApi.service.flight.dto.response;

import com.appfellas.flightApi.core.enums.FlightDirection;
import com.appfellas.flightApi.service.flight.entity.embeddable.FlightRoute;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public class FlightResponse {

    private String id;
    private LocalDateTime lastUpdatedAt;
    private LocalDateTime actualLandingTime;
    private LocalDateTime estimatedLandingTime;
    private LocalDateTime expectedTimeOnBelt;
    private FlightDirection flightDirection;
    private String flightName;
    private Integer flightNumber;
    private Boolean isOperationalFlight;
    private LocalDateTime scheduledDateTime;
    private LocalDate scheduleDate;
    private LocalTime scheduleTime;
    private Integer capacity;
    private Integer terminal;
    private String airlineName;
    private FlightRoute route;
    private Long price;
    private Set<String> passengerIds;

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

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
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

    public Set<String> getPassengerIds() {
        return passengerIds;
    }

    public void setPassengerIds(Set<String> passengerIds) {
        this.passengerIds = passengerIds;
    }
}
