package com.example.ticketapp.service.abstracts;

import java.util.List;

import com.example.ticketapp.dto.TicketDto;
import com.example.ticketapp.entity.Ticket;
import com.example.ticketapp.entity.User;

public interface TicketService {
	TicketDto addTicket(TicketDto ticket);
	TicketDto getTicketById(Long id);
	TicketDto updateTicket(TicketDto ticket);
	void deleteTicket(Long id);
	List<TicketDto> getTickets();
	List<TicketDto> getByUser_id(Long id);
	
}
