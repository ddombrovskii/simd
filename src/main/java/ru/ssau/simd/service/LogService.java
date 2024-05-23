package ru.ssau.simd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ssau.simd.entity.Flight;
import ru.ssau.simd.entity.Log;
import ru.ssau.simd.exception.NoEntityException;
import ru.ssau.simd.pojo.LogPojo;
import ru.ssau.simd.repository.LogRepository;
import ru.ssau.simd.repository.FlightRepository;
import ru.ssau.simd.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private LogRepository logRepository;

    // create
    public LogPojo saveLog(Long userId, Long flightId, LogPojo pojo) throws NoEntityException {
        if (!userRepository.existsById(userId))
            throw new NoEntityException(userId);

        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new NoEntityException(flightId));

        Log log = logRepository.save(Log.builder()
                .filepath(pojo.getFilepath())
                .flight(flight).build());

        return LogPojo.fromEntity(log);
    }

    // read all
    public List<LogPojo> getAllLogs(Long userId, Long flightId) throws NoEntityException {
        if (!userRepository.existsById(userId))
            throw new NoEntityException(userId);

        if (!flightRepository.existsById(flightId))
            throw new NoEntityException(flightId);

        List<Log> logs = logRepository.findLogsByFlightId(userId);
        List<LogPojo> logPojos = new ArrayList<>();

        for (Log log : logs)
            logPojos.add(LogPojo.fromEntity(log));

        return logPojos;
    }

    // read
    public LogPojo getLogById(Long userId, Long flightId, Long id) throws NoEntityException {
        if (!userRepository.existsById(userId))
            throw new NoEntityException(userId);

        if (!flightRepository.existsById(flightId))
            throw new NoEntityException(flightId);

        Log log = logRepository.findById(id)
                .orElseThrow(() -> new NoEntityException(id));

        return LogPojo.fromEntity(log);
    }

    // update
    public LogPojo updateLog(Long userId, Long flightId, Long id, LogPojo pojo) throws NoEntityException {
        if (!userRepository.existsById(userId))
            throw new NoEntityException(userId);

        if (!flightRepository.existsById(flightId))
            throw new NoEntityException(flightId);

        Log logToUpdate = logRepository.findById(id)
                .orElseThrow(() -> new NoEntityException(id));

        logToUpdate.setFilepath(pojo.getFilepath());

        return LogPojo.fromEntity(logRepository.save(logToUpdate));
    }

    // delete
    public void deleteLog(Long userId, Long flightId, Long id) throws NoEntityException {
        if (!userRepository.existsById(userId))
            throw new NoEntityException(userId);

        if (!flightRepository.existsById(flightId))
            throw new NoEntityException(flightId);

        Log log = logRepository.findById(id)
                .orElseThrow(() -> new NoEntityException(id));

        logRepository.deleteById(id);
    }
}
