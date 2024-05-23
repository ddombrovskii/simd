package ru.ssau.simd.pojo;

import lombok.Data;
import ru.ssau.simd.entity.Flight;

@Data
public class FlightPojo {
    private Long id;
    private String home;
    private String plan;

    public static FlightPojo fromEntity(Flight flight) {
        FlightPojo pojo = new FlightPojo();

        pojo.setId(flight.getId());
        pojo.setHome(flight.getHome());
        pojo.setPlan(flight.getPlan());

        return pojo;
    }

    public static Flight toEntity(FlightPojo pojo) {
        Flight flight = new Flight();

        flight.setId(pojo.getId());
        flight.setHome(pojo.getHome());
        flight.setPlan(pojo.getPlan());

        return flight;
    }
}
