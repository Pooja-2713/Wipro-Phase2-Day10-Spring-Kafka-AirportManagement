package com.day10.Airport_management.service;

import com.day10.Airport_management.dto.FlightApprovalEvent;
import com.day10.Airport_management.dto.FlightEvent;
import com.day10.Airport_management.dto.GateAssignmentEvent;
import com.day10.Airport_management.entity.FlightApproval;
import com.day10.Airport_management.repository.FlightApprovalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ATCService {

    @Autowired
    private FlightApprovalRepository flightApprovalRepository;

    @Autowired
    private KafkaTemplate<String, FlightApprovalEvent> kafkaTemplate;

    private static final String INPUT_TOPIC = "gate-assigned";
    private static final String OUTPUT_TOPIC = "flight-approved";

    @KafkaListener(topics = INPUT_TOPIC, groupId = "atc-group")
    public void processGateAssignment(GateAssignmentEvent event){
        System.out.println("Received gate assignment for flight: " + event.getFlightNumber());

        FlightApproval flightApproval = new FlightApproval(event.getFlightNumber(), event.getGate(), "Approved");
        flightApprovalRepository.save(flightApproval);

        FlightApprovalEvent approvalEvent = new FlightApprovalEvent(event.getFlightNumber(), "Approved");
        kafkaTemplate.send(OUTPUT_TOPIC, approvalEvent);
        System.out.println("Flight approved: " + event.getFlightNumber() + " " + event.getGate() + " " + event.getStatus());
    }
}
