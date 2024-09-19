package com.appfellas.flightApi.service.airline.controller;

import com.appfellas.flightApi.service.airline.dto.input.AirlineInput;
import com.appfellas.flightApi.service.airline.dto.response.AirlineResponse;
import com.appfellas.flightApi.service.airline.service.AirlineService;
import com.appfellas.flightApi.util.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airline")
public class AirlineController {

    private final AirlineService airlineService;

    @Autowired
    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AirlineResponse>> getAllAirlines(){
        return ResponseEntity.ok().body(airlineService.findAll().stream().map(EntityMapper::airline).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirlineResponse> getAirlineById(@PathVariable String id) {
        return ResponseEntity.ok().body(EntityMapper.airline(airlineService.findById(id)));
    }

    @GetMapping("/IATACode")
    public ResponseEntity<AirlineResponse> getAirlineByIATACode(@RequestParam(name = "code") String code) {
        return ResponseEntity.ok().body(EntityMapper.airline(airlineService.findByIATACode(code)));
    }

    @PostMapping("/create")
    public ResponseEntity<String> saveAirline(@RequestBody AirlineInput input){
        airlineService.save(input);
        return ResponseEntity.ok().body("");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAirline(@PathVariable String id) {
        airlineService.delete(id);
        return ResponseEntity.ok().body("");
    }
}
