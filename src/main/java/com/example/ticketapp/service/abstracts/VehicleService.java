package com.example.ticketapp.service.abstracts;

import java.util.List;

import com.example.ticketapp.entity.Vehicle;

public interface VehicleService {
	Vehicle addVehicle(Vehicle vehicle);
	Vehicle updateVehicle(Vehicle vehicle);
	void deleteVehicle(Long id);

	Vehicle getVehicleById(Long id);
	
	List<Vehicle> getVehicles();
	
	
}
