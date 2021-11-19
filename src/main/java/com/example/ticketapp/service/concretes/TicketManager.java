package com.example.ticketapp.service.concretes;

import java.util.ArrayList;
import java.util.Date;
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
		if(newTicket.getVehicle().getAvailableCapacity() == 0) {
			 throw new IllegalArgumentException("This vehicle has no available seat!");
		}
		newTicket.getVehicle().setAvailableCapacity(newTicket.getVehicle().getAvailableCapacity() - 1);
		
		
		this.ticketRepository.save(newTicket);
		
		dto.setStatus(Status.TAKEN);
		dto.setUserId(ticket.getUserId());
		dto.setVehicleId(ticket.getVehicleId());
		dto.setRouteId(ticket.getRouteId());
		dto.setId(this.ticketRepository.getById(newTicket.getId()).getId());
				
		dto.setAvailableCapacity(newTicket.getVehicle().getAvailableCapacity());
		dto.setSeatingCapacity(newTicket.getVehicle().getSeatingCapacity());
		dto.setVehicleDate(newTicket.getVehicle().getVehicleDate());
		dto.setVehicleName(newTicket.getVehicle().getName());
		
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
			
			dto.setAvailableCapacity(ticket.getVehicle().getAvailableCapacity());
			dto.setSeatingCapacity(ticket.getVehicle().getSeatingCapacity());
			dto.setVehicleDate(ticket.getVehicle().getVehicleDate());
			dto.setVehicleName(ticket.getVehicle().getName());
			
			dtoList.add(dto);
			
		}
		
		return dtoList;
	}

	@Override
	public void deleteTicket(Long id) {
		this.ticketRepository.deleteById(id);
		
	}

	@Override
	public List<TicketDto> getByUser_id(Long id) {
		List<TicketDto> ticketList = new ArrayList<TicketDto>();
		List<Ticket> items = this.ticketRepository.findUserByUser_Id(id);
		for (Ticket item : items) {
			TicketDto dto = new TicketDto();
			
			dto.setId(item.getId());
			dto.setStatus(item.getStatus());
			dto.setUserId(item.getUser().getId());
			dto.setVehicleId(item.getVehicle().getId());
			dto.setRouteId(item.getRoute().getId());
			User user = this.userRepository.findById(item.getUser().getId()).orElse(null);
			Route route = this.routeRepository.findById(item.getRoute().getId()).orElse(null);			
			dto.setTicketOwner(user.getFullname());
			dto.setRouteStart(route.getBegin());
			dto.setRouteEnd(route.getEnd());
			
			dto.setAvailableCapacity(item.getVehicle().getAvailableCapacity());
			dto.setSeatingCapacity(item.getVehicle().getSeatingCapacity());
			dto.setVehicleDate(item.getVehicle().getVehicleDate());
			dto.setVehicleName(item.getVehicle().getName());
			
			ticketList.add(dto);
			
			
//			dto.setId(item.getId());
//			dto.setRouteId(item.getRoute().getId());
//			dto.setStatus(item.getStatus());
//			dto.setUserId(item.getUser().getId());
//			dto.setVehicleId(item.getVehicle().getId());
//			
//			User user = this.userRepository.findById(dto.getUserId()).orElse(null);
//			Route route = this.routeRepository.findById(dto.getRouteId()).orElse(null);	
//			dto.setRouteEnd(route.getEnd());
//			dto.setRouteStart(route.getBegin());
//			dto.setTicketOwner(user.getFullname());
//				
//			ticketList.add(dto);
		}
		return ticketList;
	}

	@Override
	public TicketDto updateTicket(TicketDto ticket) {
		//User tmpUser = this.userRepository.findById(ticket.getUserId()).orElse(null);
		Ticket newTicket = new Ticket();
		TicketDto dto = new TicketDto();
		
		newTicket.setStatus(ticket.getStatus());
		
		newTicket.setUser(this.userRepository.findById(ticket.getUserId()).orElse(null));
		newTicket.setId(ticket.getId());
		newTicket.setVehicle(this.vehicleRepository.findById(ticket.getVehicleId()).orElse(null));
		newTicket.setRoute(this.routeRepository.findById(ticket.getRouteId()).orElse(null));
		if(ticket.getStatus().equals(Status.DELAYED) || ticket.getStatus().equals(Status.CANCALLED)) {
			newTicket.getVehicle().setAvailableCapacity(newTicket.getVehicle().getAvailableCapacity() + 1);
		}
		this.ticketRepository.save(newTicket);
		
		dto.setStatus(ticket.getStatus());
		dto.setUserId(ticket.getUserId());
		dto.setVehicleId(ticket.getVehicleId());
		dto.setRouteId(ticket.getRouteId());
		dto.setId(this.ticketRepository.getById(newTicket.getId()).getId());
		User user = this.userRepository.findById(dto.getUserId()).orElse(null);
		Route route = this.routeRepository.findById(dto.getRouteId()).orElse(null);	
		dto.setRouteEnd(route.getEnd());
		dto.setRouteStart(route.getBegin());
		dto.setTicketOwner(user.getFullname());
		dto.setVehicleDate(ticket.getVehicleDate());
		dto.setVehicleName(ticket.getVehicleName());
		return dto;
	}

	@Override
	public List<TicketDto> findByStatus(Status status) {
		
		List<Ticket> ticketList = this.ticketRepository.findAllByStatus(status);
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
			
			dto.setAvailableCapacity(ticket.getVehicle().getAvailableCapacity());
			dto.setSeatingCapacity(ticket.getVehicle().getSeatingCapacity());
			dto.setVehicleDate(ticket.getVehicle().getVehicleDate());
			dto.setVehicleName(ticket.getVehicle().getName());
			
			dtoList.add(dto);
			
		}
		
		return dtoList;
	}

	@Override
	public List<TicketDto> findAllByStatusAndVehicle_VehicleDate(Status status, Date vehicleDate) {

		List<Ticket> ticketList = this.ticketRepository.findAllByStatusAndVehicle_VehicleDate(status,vehicleDate);
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
			
			dto.setAvailableCapacity(ticket.getVehicle().getAvailableCapacity());
			dto.setSeatingCapacity(ticket.getVehicle().getSeatingCapacity());
			dto.setVehicleDate(ticket.getVehicle().getVehicleDate());
			dto.setVehicleName(ticket.getVehicle().getName());
			
			dtoList.add(dto);
			
		}
		
		return dtoList;
	}

	@Override
	public List<TicketDto> findAllByStatusAndVehicle_VehicleDateAndRoute_BeginAndRoute_End(Status status,
			Date vehicleDate, String begin, String end) {
		
		List<Ticket> ticketList = this.ticketRepository.findAllByStatusAndVehicle_VehicleDateAndRoute_BeginAndRoute_End(status,vehicleDate,begin,end);
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
			
			dto.setAvailableCapacity(ticket.getVehicle().getAvailableCapacity());
			dto.setSeatingCapacity(ticket.getVehicle().getSeatingCapacity());
			dto.setVehicleDate(ticket.getVehicle().getVehicleDate());
			dto.setVehicleName(ticket.getVehicle().getName());
			
			dtoList.add(dto);
			
		}
		
		return dtoList;
	}

	@Override
	public List<TicketDto> findAllByStatusAndVehicle_VehicleDateAndRoute_BeginAndRoute_EndAndUser_Id(Status status,
			Date vehicleDate, String begin, String end, Long id) {
		
		List<Ticket> ticketList = this.ticketRepository.findAllByStatusAndVehicle_VehicleDateAndRoute_BeginAndRoute_EndAndUser_Id(status,vehicleDate,begin,end,id);
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
			
			dto.setAvailableCapacity(ticket.getVehicle().getAvailableCapacity());
			dto.setSeatingCapacity(ticket.getVehicle().getSeatingCapacity());
			dto.setVehicleDate(ticket.getVehicle().getVehicleDate());
			dto.setVehicleName(ticket.getVehicle().getName());
			
			dtoList.add(dto);
			
		}
		
		return dtoList;
	}

}
