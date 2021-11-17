package com.example.ticketapp.service.abstracts;

import java.util.List;

import com.example.ticketapp.dto.VehicleDto;
import com.example.ticketapp.entity.Vehicle;

public interface VehicleService {
	VehicleDto addVehicle(VehicleDto vehicleDto);
	VehicleDto updateVehicle(VehicleDto vehicleDto);
	void deleteVehicle(Long id);

	Vehicle getVehicleById(Long id);
	
	List<VehicleDto> getVehicles();
	
	
}
