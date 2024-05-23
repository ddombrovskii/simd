package ru.ssau.simd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ssau.simd.entity.Flight;
import ru.ssau.simd.entity.User;
import ru.ssau.simd.exception.NoEntityException;
import ru.ssau.simd.pojo.FlightPojo;
import ru.ssau.simd.repository.FlightRepository;
import ru.ssau.simd.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private UserRepository userRepository;

    // create
    public FlightPojo saveFlight(Long userId, FlightPojo pojo) throws NoEntityException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoEntityException(userId));

        Flight flight = flightRepository.save(Flight.builder()
                .home(pojo.getHome())
                .plan(pojo.getPlan())
                .user(user).build());

        return FlightPojo.fromEntity(flight);
    }

    // read all
    public List<FlightPojo> getAllFlights(Long userId) throws NoEntityException {
        if (!userRepository.existsById(userId))
                throw new NoEntityException(userId);

        List<Flight> flights = flightRepository.findFlightsByUserId(userId);
        List<FlightPojo> flightPojos = new ArrayList<>();

        for (Flight flight : flights)
            flightPojos.add(FlightPojo.fromEntity(flight));

        return flightPojos;
    }

    // read
    public FlightPojo getFlightById(Long userId, Long id) throws NoEntityException {
        if (!userRepository.existsById(userId))
            throw new NoEntityException(userId);

        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new NoEntityException(id));

        return FlightPojo.fromEntity(flight);
    }

    // update
    public FlightPojo updateFlight(Long userId, Long id, FlightPojo pojo) throws NoEntityException {
        if (!userRepository.existsById(userId))
            throw new NoEntityException(userId);

        Flight flightToUpdate = flightRepository.findById(id)
                .orElseThrow(() -> new NoEntityException(id));

        flightToUpdate.setHome(pojo.getHome());
        flightToUpdate.setPlan(pojo.getPlan());

        return FlightPojo.fromEntity(flightRepository.save(flightToUpdate));
    }

    // delete
    public void deleteFlight(Long userId, Long id) throws NoEntityException {
        if (!userRepository.existsById(userId))
            throw new NoEntityException(userId);

        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new NoEntityException(id));

        flightRepository.deleteById(id);
    }
}
