package com.day10.Airport_management.dto;

public class FlightApprovalEvent {
    private String flightNumber;
    private String status;

    public FlightApprovalEvent(){

    }

    public FlightApprovalEvent(String flightNumber, String status) {
        this.flightNumber = flightNumber;
        this.status = status;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
