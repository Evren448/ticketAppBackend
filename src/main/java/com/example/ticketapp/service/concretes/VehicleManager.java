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
		
		Route route = this.routeRepository.getById(vehicleDto.getRouteId());
		
		newVehicle.setName(vehicleDto.getName());
		newVehicle.setRoute(route);
		newVehicle.setSeatingCapacity(vehicleDto.getSeatingCapacity());
		newVehicle.setVehicleDate(vehicleDto.getVehicleDate());
		newVehicle.setAvailableCapacity(vehicleDto.getSeatingCapacity());
		
		Vehicle addedVehicle = this.vehicleRepository.save(newVehicle);
	
		dto.setId(addedVehicle.getId());
		dto.setName(addedVehicle.getName());
		dto.setBeginPoint(addedVehicle.getRoute().getBegin());
		dto.setEndPoint(addedVehicle.getRoute().getEnd());
		dto.setRouteId(addedVehicle.getRoute().getId());
		dto.setSeatingCapacity(addedVehicle.getSeatingCapacity());
		dto.setVehicleDate(addedVehicle.getVehicleDate());
		dto.setAvailableCapacity(addedVehicle.getSeatingCapacity());
			
	
		return dto;

	}

	@Override
	public Vehicle getVehicleById(Long id) {
		return this.vehicleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Boyle bir vehicle yok."));
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
			dto.setVehicleDate(vehicle.getVehicleDate());
			dto.setAvailableCapacity(vehicle.getAvailableCapacity());
			
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
		
		Route route = this.routeRepository.findById(vehicleDto.getRouteId()).orElseThrow(() -> new IllegalArgumentException("Boyle bir route yok."));
		Vehicle vehicle = this.vehicleRepository.findById(vehicleDto.getId()).orElseThrow(() -> new IllegalArgumentException("Boyle bir vehicle yok."));
		
		updateVehicle.setName(vehicleDto.getName());
		updateVehicle.setRoute(route);
		updateVehicle.setId(vehicle.getId());
		updateVehicle.setSeatingCapacity(vehicleDto.getSeatingCapacity());
		updateVehicle.setVehicleDate(vehicleDto.getVehicleDate());

		//Todo araba yeni koltuk guncelleme.
		updateVehicle.setAvailableCapacity(vehicle.getAvailableCapacity());
		
		Vehicle changedVehicle = this.vehicleRepository.save(updateVehicle);
	
		dto.setId(changedVehicle.getId());
		dto.setName(changedVehicle.getName());
		dto.setBeginPoint(changedVehicle.getRoute().getBegin());
		dto.setEndPoint(changedVehicle.getRoute().getEnd());
		dto.setRouteId(route.getId());
		dto.setSeatingCapacity(changedVehicle.getSeatingCapacity());
		dto.setVehicleDate(changedVehicle.getVehicleDate());
		dto.setAvailableCapacity(changedVehicle.getSeatingCapacity());
			
		return dto;
		
	}

	@Override
	public void deleteVehicle(Long id) {
		Vehicle vehicle = this.vehicleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Boyle bir vehicle yok."));
		this.vehicleRepository.deleteById(vehicle.getId());
		
	}

	@Override
	public List<VehicleDto> findByRoute(String start, String end) {
		
		List<Vehicle> vehicleList = this.vehicleRepository.findByRoute_BeginAndRoute_End(start, end);
		List<VehicleDto> dtoList = new ArrayList<VehicleDto>();
		
		for (Vehicle vehicle : vehicleList) {
			
			VehicleDto dto = new VehicleDto();
			
			dto.setId(vehicle.getId());
			dto.setName(vehicle.getName());
			dto.setAvailableCapacity(vehicle.getAvailableCapacity());
			dto.setBeginPoint(vehicle.getRoute().getBegin());
			dto.setEndPoint(vehicle.getRoute().getEnd());
			dto.setRouteId(vehicle.getRoute().getId());
			dto.setSeatingCapacity(vehicle.getSeatingCapacity());
			dto.setVehicleDate(vehicle.getVehicleDate());

			dtoList.add(dto);	
		}
		return dtoList;
	}

}
