package com.day10.Airport_management.service;

import com.day10.Airport_management.dto.FlightEvent;
import com.day10.Airport_management.entity.Flight;
import com.day10.Airport_management.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private KafkaTemplate<String, FlightEvent> kafkaTemplate;

    private static final String TOPIC = "flight-scheduled";

    public void scheduleFlight(Flight flight){
        System.out.println("Received flight details: " + flight.getFlightNumber() + " " + flight.getSource() + " " + flight.getDestination());

        FlightEvent event = new FlightEvent(flight.getFlightNumber(), flight.getSource(), flight.getDestination(), flight.getDepartureTime(), flight.getStatus());
        flightRepository.save(flight);
        kafkaTemplate.send(TOPIC, event);

        System.out.println("Flight scheduled: " + event.getFlightNumber() + " " + event.getStatus());
    }
}
