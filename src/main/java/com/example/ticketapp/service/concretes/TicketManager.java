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
import com.example.ticketapp.entity.Vehicle;
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
		

		Ticket newTicket = new Ticket();
		TicketDto dto = new TicketDto();
		
		User tmpUser = this.userRepository.findById(ticket.getUserId()).orElseThrow(() -> new IllegalArgumentException("Boyle bir user yok."));
		Vehicle tmpVehicle = this.vehicleRepository.findById(ticket.getVehicleId()).orElseThrow(() -> new IllegalArgumentException("Boyle bir vehicle yok."));
		Route tmpRoute = this.routeRepository.findById(ticket.getRouteId()).orElseThrow(() -> new IllegalArgumentException("Boyle bir route yok."));
		
		newTicket.setStatus(Status.TAKEN);
		newTicket.setUser(tmpUser);
		newTicket.setVehicle(tmpVehicle);
		newTicket.setRoute(tmpRoute);
		if(newTicket.getVehicle().getAvailableCapacity() == 0) {
			 throw new IllegalArgumentException("This vehicle has no available seat!");
		}
		newTicket.getVehicle().setAvailableCapacity(newTicket.getVehicle().getAvailableCapacity() - 1);
		
		
		Ticket addedTicket = this.ticketRepository.save(newTicket);
		
		dto.setStatus(Status.TAKEN);
		dto.setUserId(addedTicket.getUser().getId());
		dto.setVehicleId(addedTicket.getVehicle().getId());
		dto.setRouteId(addedTicket.getRoute().getId());
		dto.setId(addedTicket.getId());
				
		dto.setAvailableCapacity(addedTicket.getVehicle().getAvailableCapacity());
		dto.setSeatingCapacity(addedTicket.getVehicle().getSeatingCapacity());
		dto.setVehicleDate(addedTicket.getVehicle().getVehicleDate());
		dto.setVehicleName(addedTicket.getVehicle().getName());
		
		return dto;
	}

	@Override
	public TicketDto getTicketById(Long id) {
		
		TicketDto dto = new TicketDto();
		Ticket ticket = this.ticketRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Boyle bir ticket yok."));
		
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
			
			User user = this.userRepository.findById(ticket.getUser().getId()).orElseThrow(() -> new IllegalArgumentException("Boyle bir user yok."));
			Route route = this.routeRepository.findById(ticket.getRoute().getId()).orElseThrow(() -> new IllegalArgumentException("Boyle bir route yok."));

			dto.setId(ticket.getId());
			dto.setStatus(ticket.getStatus());
			dto.setUserId(ticket.getUser().getId());
			dto.setVehicleId(ticket.getVehicle().getId());
			dto.setRouteId(ticket.getRoute().getId());
						
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
		Ticket ticket = this.ticketRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Boyle bir ticket yok."));
		this.ticketRepository.deleteById(id);
		
	}

	@Override
	public List<TicketDto> getByUser_id(Long id) {
		List<TicketDto> ticketList = new ArrayList<TicketDto>();
		List<Ticket> items = this.ticketRepository.findUserByUser_Id(id);
		for (Ticket item : items) {
			TicketDto dto = new TicketDto();
			
			User user = this.userRepository.findById(item.getUser().getId()).orElseThrow(() -> new IllegalArgumentException("Boyle bir user yok."));
			Route route = this.routeRepository.findById(item.getRoute().getId()).orElseThrow(() -> new IllegalArgumentException("Boyle bir route yok."));
			
			dto.setId(item.getId());
			dto.setStatus(item.getStatus());
			dto.setUserId(item.getUser().getId());
			dto.setVehicleId(item.getVehicle().getId());
			dto.setRouteId(item.getRoute().getId());
						
			dto.setTicketOwner(user.getFullname());
			dto.setRouteStart(route.getBegin());
			dto.setRouteEnd(route.getEnd());
			
			dto.setAvailableCapacity(item.getVehicle().getAvailableCapacity());
			dto.setSeatingCapacity(item.getVehicle().getSeatingCapacity());
			dto.setVehicleDate(item.getVehicle().getVehicleDate());
			dto.setVehicleName(item.getVehicle().getName());
			
			ticketList.add(dto);
		}
		return ticketList;
	}

	@Override
	public TicketDto updateTicket(TicketDto ticket) {
		//User tmpUser = this.userRepository.findById(ticket.getUserId()).orElse(null);
		Ticket newTicket = new Ticket();
		TicketDto dto = new TicketDto();
		
		User tmpUser = this.userRepository.findById(ticket.getUserId()).orElseThrow(() -> new IllegalArgumentException("Boyle bir user yok."));
		Vehicle tmpVehicle = this.vehicleRepository.findById(ticket.getVehicleId()).orElseThrow(() -> new IllegalArgumentException("Boyle bir vehicle yok."));
		Route tmpRoute = this.routeRepository.findById(ticket.getRouteId()).orElseThrow(() -> new IllegalArgumentException("Boyle bir route yok."));
		
		newTicket.setStatus(ticket.getStatus());
		
		newTicket.setUser(tmpUser);
		newTicket.setId(ticket.getId());
		newTicket.setVehicle(tmpVehicle);
		newTicket.setRoute(tmpRoute);
		if(ticket.getStatus().equals(Status.DELAYED) || ticket.getStatus().equals(Status.CANCALLED)) {
			newTicket.getVehicle().setAvailableCapacity(newTicket.getVehicle().getAvailableCapacity() + 1);
		}
		
		Ticket updatedTicket = this.ticketRepository.save(newTicket);
		
		dto.setStatus(updatedTicket.getStatus());
		dto.setUserId(updatedTicket.getUser().getId());
		dto.setVehicleId(updatedTicket.getVehicle().getId());
		dto.setRouteId(updatedTicket.getRoute().getId());
		dto.setId(updatedTicket.getId());	
		dto.setRouteEnd(updatedTicket.getRoute().getEnd());
		dto.setRouteStart(updatedTicket.getRoute().getBegin());
		dto.setTicketOwner(updatedTicket.getUser().getFullname());
		dto.setVehicleDate(updatedTicket.getVehicle().getVehicleDate());
		dto.setVehicleName(updatedTicket.getVehicle().getName());
		
		return dto;
	}

	@Override
	public List<TicketDto> findByStatus(Status status) {
		
		List<Ticket> ticketList = this.ticketRepository.findAllByStatus(status);
		List<TicketDto> dtoList = new ArrayList<TicketDto>();
		
		for (Ticket ticket : ticketList) {
			TicketDto dto = new TicketDto();
			
			User user = this.userRepository.findById(ticket.getUser().getId()).orElseThrow(() -> new IllegalArgumentException("boyle bir user yok."));
			Route route = this.routeRepository.findById(ticket.getRoute().getId()).orElseThrow(() -> new IllegalArgumentException("boyle bir route yok."));

			dto.setId(ticket.getId());
			dto.setStatus(ticket.getStatus());
			dto.setUserId(ticket.getUser().getId());
			dto.setVehicleId(ticket.getVehicle().getId());
			dto.setRouteId(ticket.getRoute().getId());
					
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
			
			User user = this.userRepository.findById(ticket.getUser().getId()).orElseThrow(() -> new IllegalArgumentException("boyle bir user yok."));
			Route route = this.routeRepository.findById(ticket.getRoute().getId()).orElseThrow(() -> new IllegalArgumentException("boyle bir route yok."));


			dto.setId(ticket.getId());
			dto.setStatus(ticket.getStatus());
			dto.setUserId(ticket.getUser().getId());
			dto.setVehicleId(ticket.getVehicle().getId());
			dto.setRouteId(ticket.getRoute().getId());
			
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

			User user = this.userRepository.findById(ticket.getUser().getId()).orElseThrow(() -> new IllegalArgumentException("boyle bir user yok."));
			Route route = this.routeRepository.findById(ticket.getRoute().getId()).orElseThrow(() -> new IllegalArgumentException("boyle bir route yok."));

			
			dto.setId(ticket.getId());
			dto.setStatus(ticket.getStatus());
			dto.setUserId(ticket.getUser().getId());
			dto.setVehicleId(ticket.getVehicle().getId());
			dto.setRouteId(ticket.getRoute().getId());
				
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

			User user = this.userRepository.findById(ticket.getUser().getId()).orElseThrow(() -> new IllegalArgumentException("boyle bir user yok."));
			Route route = this.routeRepository.findById(ticket.getRoute().getId()).orElseThrow(() -> new IllegalArgumentException("boyle bir route yok."));

			dto.setId(ticket.getId());
			dto.setStatus(ticket.getStatus());
			dto.setUserId(ticket.getUser().getId());
			dto.setVehicleId(ticket.getVehicle().getId());
			dto.setRouteId(ticket.getRoute().getId());
					
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
