package com.example.ticketapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ticketapp.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	List<Vehicle> findByRoute_BeginAndRoute_End(String start, String end);
	Vehicle findRouteByRoute_Id(Long id);
	
}
