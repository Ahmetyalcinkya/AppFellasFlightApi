package com.appfellas.flightApi.service.flight.controller;

import com.appfellas.flightApi.core.enums.Role;
import com.appfellas.flightApi.core.enums.SortDirection;
import com.appfellas.flightApi.core.enums.SortProperty;
import com.appfellas.flightApi.core.security.SecurityContextUtils;
import com.appfellas.flightApi.service.flight.dto.input.FlightInput;
import com.appfellas.flightApi.service.flight.dto.response.FlightResponse;
import com.appfellas.flightApi.service.flight.entity.Flight;
import com.appfellas.flightApi.service.flight.service.FlightService;
import com.appfellas.flightApi.service.user.entity.User;
import com.appfellas.flightApi.service.user.service.UserService;
import com.appfellas.flightApi.util.EntityMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/flight")
public class FlightController {

    private final FlightService flightService;
    private final UserService userService;

    @Autowired
    public FlightController(FlightService flightService, UserService userService) {
        this.flightService = flightService;
        this.userService = userService;
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/all")
    public ResponseEntity<List<FlightResponse>> getAllFlights() {
        return ResponseEntity.ok().body(flightService.findAll().stream().map(EntityMapper::flight).toList());
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/{id}")
    public ResponseEntity<FlightResponse> getFlightById(@PathVariable String id) {
        return ResponseEntity.ok().body(EntityMapper.flight(flightService.findById(id)));
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/name")
    public ResponseEntity<FlightResponse> getFlightByName(@RequestParam(name = "name") String name) {
        return ResponseEntity.ok().body(EntityMapper.flight(flightService.findByFlightName(name)));
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/filter-date-between")
    public ResponseEntity<List<FlightResponse>> filterFlightByDate(@RequestParam(name = "start") LocalDate startDate, @RequestParam(name = "end") LocalDate endDate) {
        return ResponseEntity.ok().body(flightService.filterFlightByDate(startDate, endDate).stream().map(EntityMapper::flight).toList());
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/filter-time-between")
    public ResponseEntity<List<FlightResponse>> filterFlightByTime(@RequestParam(name = "start") LocalTime startDate, @RequestParam(name = "end") LocalTime endDate) {
        return ResponseEntity.ok().body(flightService.filterFlightByTime(startDate, endDate).stream().map(EntityMapper::flight).toList());
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/filter-airline/{id}")
    public ResponseEntity<List<FlightResponse>> filterFlightByAirline(@PathVariable String id) {
        return ResponseEntity.ok().body(flightService.filterFlightByAirline(id).stream().map(EntityMapper::flight).toList());
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/filter-flights")
    public ResponseEntity<List<FlightResponse>> filterFlightByAirline(@RequestParam(name = "startDate") LocalDate startDate, @RequestParam(name = "endDate") LocalDate endDate, @RequestParam(name = "startTime") LocalTime startTime, @RequestParam(name = "endTime") LocalTime endTime, @RequestParam(name = "airline") String airlineId, @RequestParam(name = "property") String property, @RequestParam(name = "direction") String direction, @RequestParam(name = "departureAirportIATA") String departureAirportIATA, @RequestParam(name = "arrivalAirportIATA") String arrivalAirportIATA) {
        return ResponseEntity.ok().body(flightService.filterFlight(startDate, endDate, startTime, endTime, airlineId, SortProperty.valueOf(property), SortDirection.valueOf(direction), departureAirportIATA, arrivalAirportIATA).stream().map(EntityMapper::flight).toList());
    }

    @GetMapping("/users-flights")
    public ResponseEntity<List<FlightResponse>> findAllFlightByIds() {
        String principal = SecurityContextUtils.getPrincipal(SecurityContextHolder.getContext().getAuthentication(), Role.USER);
        User user = userService.findById(principal);
        return ResponseEntity.ok().body(flightService.findAllFlightByIds(user.getFlights().stream().map(Flight::getId).toList()).stream().map(EntityMapper::flight).toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<String> saveFlight(@RequestBody FlightInput input) {
        flightService.save(input);
        return ResponseEntity.ok().body("");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateFlight(@PathVariable String id, @RequestBody FlightInput input) {
        flightService.update(id, input);
        return ResponseEntity.ok().body("");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFlight(@PathVariable String id) {
        flightService.delete(id);
        return ResponseEntity.ok().body("");
    }

}
