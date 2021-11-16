package com.example.ticketapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticketapp.dto.TicketDto;
import com.example.ticketapp.entity.Route;
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
}
