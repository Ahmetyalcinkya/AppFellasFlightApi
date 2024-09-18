package com.appfellas.flightApi.service.flight.dto.input;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class FlightInput {

    private LocalDateTime lastUpdatedAt;
    private LocalDateTime actualLandingTime;
    private AirCraftTypeInput airCraftType;
    private LocalDateTime estimatedLandingTime;
    private LocalDateTime expectedTimeOnBelt;
    private String flightDirection;
    private String flightName;
    private Integer flightNumber;
    private Boolean isOperationalFlight;
    private String prefixIATA;
    private LocalDateTime scheduledDateTime;
    private LocalDate scheduleDate;
    private LocalTime scheduleTime;
    private Integer terminal;
    private FlightRouteInput route;

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

    public String getPrefixIATA() {
        return prefixIATA;
    }

    public void setPrefixIATA(String prefixIATA) {
        this.prefixIATA = prefixIATA;
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
