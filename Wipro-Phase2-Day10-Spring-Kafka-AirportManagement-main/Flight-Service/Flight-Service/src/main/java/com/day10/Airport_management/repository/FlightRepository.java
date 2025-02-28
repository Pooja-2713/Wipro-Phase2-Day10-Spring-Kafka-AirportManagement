package com.day10.Airport_management.repository;

import com.day10.Airport_management.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
