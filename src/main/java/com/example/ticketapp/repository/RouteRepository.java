package com.example.ticketapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ticketapp.entity.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
	Route findByBeginAndEnd(String begin, String end);
}
