package com.example.ticketapp.service.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ticketapp.entity.Route;
import com.example.ticketapp.entity.Vehicle;
import com.example.ticketapp.repository.VehicleRepository;
import com.example.ticketapp.service.abstracts.VehicleService;

@Service
public class VehicleManager implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Override
	public Vehicle addVehicle(Vehicle vehicle) {
		return this.vehicleRepository.save(vehicle);
	}

	@Override
	public Vehicle getVehicleById(Long id) {
		return this.vehicleRepository.findById(id).orElse(null);
	}

	@Override
	public List<Vehicle> getVehicles() {
		return this.vehicleRepository.findAll();
	}

	@Override
	public Vehicle updateVehicle(Vehicle vehicle) {
		Vehicle updateVehicle = new Vehicle();
		updateVehicle.setName(vehicle.getName());
		updateVehicle.setId(vehicle.getId());
		
		return this.vehicleRepository.save(updateVehicle);
	}

	@Override
	public void deleteVehicle(Long id) {
		this.vehicleRepository.deleteById(id);
		
	}

}
