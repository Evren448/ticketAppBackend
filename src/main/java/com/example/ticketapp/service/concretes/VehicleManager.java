package com.example.ticketapp.service.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ticketapp.dto.TicketDto;
import com.example.ticketapp.dto.VehicleDto;
import com.example.ticketapp.entity.Route;
import com.example.ticketapp.entity.Status;
import com.example.ticketapp.entity.Ticket;
import com.example.ticketapp.entity.User;
import com.example.ticketapp.entity.Vehicle;
import com.example.ticketapp.repository.RouteRepository;
import com.example.ticketapp.repository.VehicleRepository;
import com.example.ticketapp.service.abstracts.VehicleService;

@Service
public class VehicleManager implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private RouteRepository routeRepository;
	
	@Override
	public VehicleDto addVehicle(VehicleDto vehicleDto) {
		
		Vehicle newVehicle = new Vehicle();
		VehicleDto dto = new VehicleDto();
		
		newVehicle.setName(vehicleDto.getName());
		newVehicle.setRoute(this.routeRepository.getById(vehicleDto.getRouteId()));
		newVehicle.setSeatingCapacity(vehicleDto.getSeatingCapacity());
		
		this.vehicleRepository.save(newVehicle);
	
		dto.setId(this.vehicleRepository.getById(newVehicle.getId()).getId());
		dto.setName(newVehicle.getName());
		dto.setBeginPoint(newVehicle.getRoute().getBegin());
		dto.setEndPoint(newVehicle.getRoute().getEnd());
		dto.setRouteId(this.routeRepository.getById(vehicleDto.getRouteId()).getId());
		dto.setSeatingCapacity(newVehicle.getSeatingCapacity());
			
	
		return dto;

	}

	@Override
	public Vehicle getVehicleById(Long id) {
		return this.vehicleRepository.findById(id).orElse(null);
	}

	@Override
	public List<VehicleDto> getVehicles() {
		
		List<Vehicle> vehicleList = this.vehicleRepository.findAll();
		List<VehicleDto> dtoList = new ArrayList<VehicleDto>();
		
		for (Vehicle vehicle : vehicleList) {
			VehicleDto dto = new VehicleDto();
			
			dto.setId(vehicle.getId());
			dto.setName(vehicle.getName());
			dto.setSeatingCapacity(vehicle.getSeatingCapacity());
			
			Route route = this.routeRepository.getById(vehicle.getRoute().getId());
			dto.setBeginPoint(route.getBegin());
			dto.setEndPoint(route.getEnd());
			dto.setRouteId(route.getId());

			dtoList.add(dto);
			
		}
		
		return dtoList;
	}

	@Override
	public VehicleDto updateVehicle(VehicleDto vehicleDto) {
		
		Vehicle updateVehicle = new Vehicle();
		VehicleDto dto = new VehicleDto();
		
		updateVehicle.setName(vehicleDto.getName());
		updateVehicle.setRoute(this.routeRepository.getById(vehicleDto.getRouteId()));
		updateVehicle.setId(this.vehicleRepository.getById(vehicleDto.getId()).getId());
		updateVehicle.setSeatingCapacity(vehicleDto.getSeatingCapacity());
		
		this.vehicleRepository.save(updateVehicle);
	
		dto.setId(this.vehicleRepository.getById(updateVehicle.getId()).getId());
		dto.setName(updateVehicle.getName());
		dto.setBeginPoint(updateVehicle.getRoute().getBegin());
		dto.setEndPoint(updateVehicle.getRoute().getEnd());
		dto.setRouteId(this.routeRepository.getById(vehicleDto.getRouteId()).getId());
		dto.setSeatingCapacity(updateVehicle.getSeatingCapacity());
			
	
		return dto;
		
		
		//Vehicle updateVehicle = new Vehicle();
		//updateVehicle.setName(vehicle.getName());
		//updateVehicle.setId(vehicle.getId());
		
		//return this.vehicleRepository.save(updateVehicle);
	}

	@Override
	public void deleteVehicle(Long id) {
		this.vehicleRepository.deleteById(id);
		
	}

}
