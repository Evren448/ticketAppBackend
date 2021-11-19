package com.example.ticketapp.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.example.ticketapp.entity.Route;
import com.example.ticketapp.entity.Status;
import com.example.ticketapp.entity.Ticket;
import com.example.ticketapp.service.abstracts.TicketService;

@RestController
@RequestMapping("api/ticket")
public class TicketController {

	@Autowired
	private TicketService ticketService;
	
	@PostMapping("add")
    public ResponseEntity<?> saveTicket(@RequestBody TicketDto ticketDto)
    {
        return new ResponseEntity<>(this.ticketService.addTicket(ticketDto), HttpStatus.CREATED);
    }
	
	@PutMapping("update")
	public ResponseEntity<?> updateTicket(@RequestBody TicketDto ticketDto) {
		return ResponseEntity.ok(this.ticketService.updateTicket(ticketDto));
	}
	
	@GetMapping("get/{id}")
    public ResponseEntity<?> saveTicket(@PathVariable("id") Long id)
    {
        return new ResponseEntity<>(this.ticketService.getTicketById(id), HttpStatus.OK);
    }
	
	@GetMapping("/getTickets")
	public ResponseEntity<?> getTickets(){
		return ResponseEntity.ok(this.ticketService.getTickets());
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteTicket(@PathVariable Long id) {
		this.ticketService.deleteTicket(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/getTicketsByUserId/{id}")
	public ResponseEntity<?> getTickets(@PathVariable("id") Long id){
		return ResponseEntity.ok(this.ticketService.getByUser_id(id));
	}
	
	@GetMapping("/getTicketsByStatus")
	public ResponseEntity<?> getTickets(Status status){
		return ResponseEntity.ok(this.ticketService.findByStatus(status));
	}
	
	@GetMapping("/findAllByStatusAndVehicle_VehicleDate")
	public ResponseEntity<?> findAllByStatusAndVehicle_VehicleDate(@RequestParam("status") Status status,@RequestParam("vehicleDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date vehicleDate){
		return ResponseEntity.ok(this.ticketService.findAllByStatusAndVehicle_VehicleDate(status, vehicleDate));
	}
	
	@GetMapping("/findAllByStatusAndVehicle_VehicleDateAndRoute_BeginAndRoute_End")
	public ResponseEntity<?> findAllByStatusAndVehicle_VehicleDateAndRoute_BeginAndRoute_End
	(@RequestParam("status") Status status, @RequestParam("vehicleDate") 
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date vehicleDate, 
	@RequestParam("begin") String begin,@RequestParam("end") String end)
	{
		return ResponseEntity.ok(this.ticketService.findAllByStatusAndVehicle_VehicleDateAndRoute_BeginAndRoute_End(status, vehicleDate, begin, end));
	}
	
	@GetMapping("/findAllByStatusAndVehicle_VehicleDateAndRoute_BeginAndRoute_EndAndUser_Id")
	public ResponseEntity<?> findAllByStatusAndVehicle_VehicleDateAndRoute_BeginAndRoute_EndAndUser_Id
	(@RequestParam("status") Status status, @RequestParam("vehicleDate") 
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date vehicleDate, 
	@RequestParam("begin") String begin,@RequestParam("end") String end,@RequestParam("id") Long id)
	{
		return ResponseEntity.ok(this.ticketService.findAllByStatusAndVehicle_VehicleDateAndRoute_BeginAndRoute_EndAndUser_Id(status, vehicleDate, begin, end, id));
	}
	
	
}
