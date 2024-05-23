package ru.ssau.simd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ssau.simd.exception.NoEntityException;
import ru.ssau.simd.pojo.FlightPojo;
import ru.ssau.simd.service.FlightService;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @PostMapping
    public ResponseEntity<FlightPojo> createFlight(@PathVariable("userId") Long userId, @RequestBody FlightPojo pojo) {
        try {
            return new ResponseEntity<>(flightService.saveFlight(userId, pojo), HttpStatus.CREATED);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<FlightPojo>> getAllFlights(@PathVariable("userId") Long userId) {
        try {
            return new ResponseEntity<>(flightService.getAllFlights(userId), HttpStatus.OK);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightPojo> getFlightById(@PathVariable("userId") Long userId, @PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(flightService.getFlightById(userId, id), HttpStatus.OK);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightPojo> updateFlight(@PathVariable("userId") Long userId, @PathVariable("id") Long id,
                                                   @RequestBody FlightPojo pojo) {
        try {
            return new ResponseEntity<>(flightService.updateFlight(userId, id, pojo), HttpStatus.OK);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable("userId") Long userId, @PathVariable("id") Long id) {
        try {
            flightService.deleteFlight(userId, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
