package com.example.ticketapp.service.abstracts;

import java.util.Date;
import java.util.List;

import com.example.ticketapp.dto.TicketDto;
import com.example.ticketapp.entity.Status;
import com.example.ticketapp.entity.Ticket;
import com.example.ticketapp.entity.User;

public interface TicketService {
	TicketDto addTicket(TicketDto ticket);
	TicketDto getTicketById(Long id);
	TicketDto updateTicket(TicketDto ticket);
	void deleteTicket(Long id);
	List<TicketDto> getTickets();
	List<TicketDto> getByUser_id(Long id);
	List<TicketDto> findByStatus(Status status);
	
	
	List<TicketDto> findAllByStatusAndVehicle_VehicleDate(Status status, Date vehicleDate);
	List<TicketDto> findAllByStatusAndVehicle_VehicleDateAndRoute_BeginAndRoute_End(Status status, Date vehicleDate, String begin, String end);

	List<TicketDto> findAllByStatusAndVehicle_VehicleDateAndRoute_BeginAndRoute_EndAndUser_Id(Status status, Date vehicleDate, String begin, String end, Long id);

}
