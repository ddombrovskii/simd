package ru.ssau.simd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ssau.simd.exception.NoEntityException;
import ru.ssau.simd.pojo.LogPojo;
import ru.ssau.simd.service.LogService;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/flights/{flightId}/log")
public class LogController {
    @Autowired
    private LogService logService;
    
    @PostMapping
    public ResponseEntity<LogPojo> createLog(@PathVariable("userId") Long userId,
                                             @PathVariable("flightId") Long flightId, @RequestBody LogPojo pojo) {
        try {
            return new ResponseEntity<>(logService.saveLog(userId, flightId, pojo), HttpStatus.CREATED);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<LogPojo>> getAllLogs(@PathVariable("userId") Long userId,
                                                    @PathVariable("flightId") Long flightId) {
        try {
            return new ResponseEntity<>(logService.getAllLogs(userId, flightId), HttpStatus.OK);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogPojo> getLogById(@PathVariable("userId") Long userId,
                                              @PathVariable("flightId") Long flightId, @PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(logService.getLogById(userId, flightId, id), HttpStatus.OK);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LogPojo> updateLog(@PathVariable("userId") Long userId,
                                             @PathVariable("flightId") Long flightId,
                                             @PathVariable("id") Long id, @RequestBody LogPojo pojo) {
        try {
            return new ResponseEntity<>(logService.updateLog(userId, flightId, id, pojo), HttpStatus.OK);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLog(@PathVariable("userId") Long userId,
                                       @PathVariable("flightId") Long flightId, @PathVariable("id") Long id) {
        try {
            logService.deleteLog(userId, flightId, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoEntityException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
