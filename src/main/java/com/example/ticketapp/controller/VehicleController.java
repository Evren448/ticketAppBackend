package com.example.ticketapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticketapp.dto.TicketDto;
import com.example.ticketapp.dto.VehicleDto;
import com.example.ticketapp.entity.Route;
import com.example.ticketapp.entity.Vehicle;
import com.example.ticketapp.service.abstracts.VehicleService;

@RestController
@RequestMapping("api/vehicle")
@CrossOrigin
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	
	@PostMapping("add")
    public ResponseEntity<?> saveVehicle(@RequestBody VehicleDto vehicleDto)
    {
        return new ResponseEntity<>(this.vehicleService.addVehicle(vehicleDto), HttpStatus.CREATED);
    }
	
	@PutMapping("update")
	public ResponseEntity<?> updateVehicle(@RequestBody VehicleDto vehicleDto) {
		return ResponseEntity.ok(this.vehicleService.updateVehicle(vehicleDto));
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
		this.vehicleService.deleteVehicle(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("getAll")
    public ResponseEntity<?> getAll()
    {
        return new ResponseEntity<>(vehicleService.getVehicles(), HttpStatus.OK);
    }
	
	@GetMapping("get/{id}")
    public ResponseEntity<?> getVehicleById(@PathVariable("id") Long id)
    {
        return new ResponseEntity<>(this.vehicleService.getVehicleById(id), HttpStatus.OK);
    }
	
	@GetMapping("getStartEnd")
    public ResponseEntity<?> getStartEnd(String begin, String end)
    {
        return new ResponseEntity<>(this.vehicleService.findByRoute(begin, end), HttpStatus.OK);
    }
	
}
