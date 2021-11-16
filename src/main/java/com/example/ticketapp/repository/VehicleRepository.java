package com.example.ticketapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ticketapp.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
