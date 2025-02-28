package com.day10.Airport_management.controller;

import com.day10.Airport_management.dto.FlightEvent;
import com.day10.Airport_management.dto.FlightRequest;
import com.day10.Airport_management.entity.Flight;
import com.day10.Airport_management.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping("/schedule")
    public ResponseEntity<String> scheduleFlight(@RequestBody FlightRequest request){
        Flight flight = new Flight(request.getFlightNumber(), request.getSource(), request.getDestination(), request.getDepartureTime(), "Scheduled");
        flightService.scheduleFlight(flight);

        return new ResponseEntity<>("Flight scheduled successfully", HttpStatus.CREATED);
    }
}
