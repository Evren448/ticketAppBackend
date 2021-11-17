package com.example.ticketapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticketapp.dto.TicketDto;
import com.example.ticketapp.service.abstracts.HomePageService;
import com.example.ticketapp.service.abstracts.TicketService;
import com.example.ticketapp.service.abstracts.VehicleService;

@RestController
@RequestMapping("api/homepage")
public class HomePageController {
	
	@Autowired
	private HomePageService homePageService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired 
	private TicketService ticketService;
	
	@GetMapping("getAll")
    public ResponseEntity<?> getAll()
    {
        return new ResponseEntity<>(vehicleService.getVehicles(), HttpStatus.OK);
    }
	
	@PostMapping("buyTicket")
    public ResponseEntity<?> saveTicket(@RequestBody TicketDto ticketDto)
    {
        return new ResponseEntity<>(this.ticketService.addTicket(ticketDto), HttpStatus.CREATED);
    }

}
