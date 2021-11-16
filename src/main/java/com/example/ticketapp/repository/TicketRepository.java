package com.example.ticketapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ticketapp.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
	Ticket getTicketById(Long id);
}
