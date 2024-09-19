package com.appfellas.flightApi.service.flight.controller;

import com.appfellas.flightApi.core.enums.SortDirection;
import com.appfellas.flightApi.core.enums.SortProperty;
import com.appfellas.flightApi.service.flight.dto.input.FlightInput;
import com.appfellas.flightApi.service.flight.dto.response.FlightResponse;
import com.appfellas.flightApi.service.flight.service.FlightService;
import com.appfellas.flightApi.util.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<FlightResponse>> getAllFlights() {
        return ResponseEntity.ok().body(flightService.findAll().stream().map(EntityMapper::flight).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightResponse> getFlightById(@PathVariable String id) {
        return ResponseEntity.ok().body(EntityMapper.flight(flightService.findById(id)));
    }

    @GetMapping("/name")
    public ResponseEntity<FlightResponse> getFlightByName(@RequestParam(name = "name") String name) {
        return ResponseEntity.ok().body(EntityMapper.flight(flightService.findByFlightName(name)));
    }

    @GetMapping("/filter-date-between")
    public ResponseEntity<List<FlightResponse>> filterFlightByDate(@RequestParam(name = "start") LocalDate startDate, @RequestParam(name = "end") LocalDate endDate) {
        return ResponseEntity.ok().body(flightService.filterFlightByDate(startDate, endDate).stream().map(EntityMapper::flight).toList());
    }

    @GetMapping("/filter-time-between")
    public ResponseEntity<List<FlightResponse>> filterFlightByTime(@RequestParam(name = "start") LocalTime startDate, @RequestParam(name = "end") LocalTime endDate) {
        return ResponseEntity.ok().body(flightService.filterFlightByTime(startDate, endDate).stream().map(EntityMapper::flight).toList());
    }

    @GetMapping("/filter-airline/{id}")
    public ResponseEntity<List<FlightResponse>> filterFlightByAirline(@PathVariable String id) {
        return ResponseEntity.ok().body(flightService.filterFlightByAirline(id).stream().map(EntityMapper::flight).toList());
    }

    @GetMapping("/filter-flights")
    public ResponseEntity<List<FlightResponse>> filterFlightByAirline(@RequestParam(name = "startDate") LocalDate startDate, @RequestParam(name = "endDate") LocalDate endDate, @RequestParam(name = "startTime") LocalTime startTime, @RequestParam(name = "endTime") LocalTime endTime, @RequestParam(name = "airline") String airlineId, @RequestParam(name = "property") String property, @RequestParam(name = "direction") String direction) {
        return ResponseEntity.ok().body(flightService.filterFlight(startDate, endDate, startTime, endTime, airlineId, SortProperty.valueOf(property), SortDirection.valueOf(direction)).stream().map(EntityMapper::flight).toList());
    }

    @PostMapping("/create")
    public ResponseEntity<String> saveFlight(@RequestBody FlightInput input) {
        flightService.save(input);
        return ResponseEntity.ok().body("");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateFlight(@PathVariable String id, @RequestBody FlightInput input) {
        flightService.update(id, input);
        return ResponseEntity.ok().body("");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFlight(@PathVariable String id) {
        flightService.delete(id);
        return ResponseEntity.ok().body("");
    }

}
