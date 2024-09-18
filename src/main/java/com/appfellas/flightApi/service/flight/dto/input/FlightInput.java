package com.appfellas.flightApi.service.flight.dto.input;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class FlightInput {

    private LocalDateTime lastUpdateAt;
    private LocalDateTime actualLandingTime;
    private AirCraftTypeInput airCraftType;
    private LocalDateTime estimatedLandingTime;
    private LocalDateTime expectedTimeOnBelt;
    private String flightDirection;
    private Integer flightNumber;
    private Boolean isOperationalFlight;
    private String mainFlight;
    private Integer airlineCode;
    private LocalDateTime scheduledDateTime;
    private LocalDate scheduleDate;
    private LocalTime scheduleTime;
    private Integer terminal;
    private FlightRouteInput route;

    public LocalDateTime getLastUpdateAt() {
        return lastUpdateAt;
    }

    public void setLastUpdateAt(LocalDateTime lastUpdateAt) {
        this.lastUpdateAt = lastUpdateAt;
    }

    public LocalDateTime getActualLandingTime() {
        return actualLandingTime;
    }

    public void setActualLandingTime(LocalDateTime actualLandingTime) {
        this.actualLandingTime = actualLandingTime;
    }

    public AirCraftTypeInput getAirCraftType() {
        return airCraftType;
    }

    public void setAirCraftType(AirCraftTypeInput airCraftType) {
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

    public String getFlightDirection() {
        return flightDirection;
    }

    public void setFlightDirection(String flightDirection) {
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

    public FlightRouteInput getRoute() {
        return route;
    }

    public void setRoute(FlightRouteInput route) {
        this.route = route;
    }
}
