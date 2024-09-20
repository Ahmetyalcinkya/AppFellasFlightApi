package com.appfellas.flightApi.service.airport.controller;

import com.appfellas.flightApi.service.airport.dto.input.AirportInput;
import com.appfellas.flightApi.service.airport.dto.response.AirportResponse;
import com.appfellas.flightApi.service.airport.service.AirportService;
import com.appfellas.flightApi.util.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airport")
public class AirportController {

    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/all")
    public ResponseEntity<List<AirportResponse>> getAllAirports(){
        return ResponseEntity.ok().body(airportService.findAll().stream().map(EntityMapper::airport).toList());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/IATACode")
    public ResponseEntity<AirportResponse> findByIATACode(@RequestParam(name = "code") String IATACode) {
        return ResponseEntity.ok().body(EntityMapper.airport(airportService.findBYIATACode(IATACode)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<String> saveAirport(@RequestBody AirportInput input) {
        airportService.save(input);
        return ResponseEntity.ok().body("");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAirport(@PathVariable String id, @RequestBody AirportInput input) {
        airportService.update(id, input);
        return ResponseEntity.ok().body("");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAirport(@PathVariable String id){
        airportService.delete(id);
        return ResponseEntity.ok().body("");
    }
}
