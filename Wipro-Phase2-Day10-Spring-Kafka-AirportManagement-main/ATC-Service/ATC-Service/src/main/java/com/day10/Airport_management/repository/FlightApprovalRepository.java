package com.day10.Airport_management.repository;

import com.day10.Airport_management.entity.FlightApproval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightApprovalRepository extends JpaRepository<FlightApproval, Long> {

}
