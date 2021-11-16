package com.example.ticketapp.service.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ticketapp.dto.TicketDto;
import com.example.ticketapp.entity.Route;
import com.example.ticketapp.entity.Status;
import com.example.ticketapp.entity.Ticket;
import com.example.ticketapp.entity.User;
import com.example.ticketapp.repository.RouteRepository;
import com.example.ticketapp.repository.TicketRepository;
import com.example.ticketapp.repository.UserRepository;
import com.example.ticketapp.repository.VehicleRepository;
import com.example.ticketapp.service.abstracts.TicketService;

@Service
public class TicketManager implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private RouteRepository routeRepository;
	
	@Override
	public TicketDto addTicket(TicketDto ticket) {
		
		User tmpUser = this.userRepository.findById(ticket.getUserId()).orElse(null);
		Ticket newTicket = new Ticket();
		TicketDto dto = new TicketDto();
		
		newTicket.setStatus(Status.TAKEN);
		newTicket.setUser(this.userRepository.findById(ticket.getUserId()).orElse(null));
		newTicket.setVehicle(this.vehicleRepository.findById(ticket.getVehicleId()).orElse(null));
		newTicket.setRoute(this.routeRepository.findById(ticket.getRouteId()).orElse(null));
		
		
		
		this.ticketRepository.save(newTicket);
		
		dto.setStatus(Status.TAKEN);
		dto.setUserId(ticket.getUserId());
		dto.setVehicleId(ticket.getVehicleId());
		dto.setRouteId(ticket.getRouteId());
		dto.setId(this.ticketRepository.getById(newTicket.getId()).getId());
		
		return dto;
	}

	@Override
	public TicketDto getTicketById(Long id) {
		
		TicketDto dto = new TicketDto();
		Ticket ticket = this.ticketRepository.getById(id);
		
		dto.setId(ticket.getId());
		dto.setStatus(ticket.getStatus());
		dto.setUserId(ticket.getUser().getId());
		dto.setVehicleId(ticket.getVehicle().getId());
		dto.setRouteId(ticket.getRoute().getId());
		
		return dto;
	}

	@Override
	public List<TicketDto> getTickets() {
		List<Ticket> ticketList = this.ticketRepository.findAll();
		List<TicketDto> dtoList = new ArrayList<TicketDto>();
		
		for (Ticket ticket : ticketList) {
			TicketDto dto = new TicketDto();

			dto.setId(ticket.getId());
			dto.setStatus(ticket.getStatus());
			dto.setUserId(ticket.getUser().getId());
			dto.setVehicleId(ticket.getVehicle().getId());
			dto.setRouteId(ticket.getRoute().getId());
			User user = this.userRepository.findById(ticket.getUser().getId()).orElse(null);
			Route route = this.routeRepository.findById(ticket.getRoute().getId()).orElse(null);			
			dto.setTicketOwner(user.getFullname());
			dto.setRouteStart(route.getBegin());
			dto.setRouteEnd(route.getEnd());
			
			dtoList.add(dto);
			
		}
		
		return dtoList;
	}

	@Override
	public void deleteTicket(Long id) {
		this.ticketRepository.deleteById(id);
		
	}

}
