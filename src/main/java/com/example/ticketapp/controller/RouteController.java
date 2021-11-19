package com.example.ticketapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticketapp.entity.Route;
import com.example.ticketapp.entity.User;
import com.example.ticketapp.service.abstracts.RouteService;

@RestController
@RequestMapping("/api/route/")
public class RouteController {
	
	@Autowired
	private RouteService routeService;

	
	@PostMapping("add")
    public ResponseEntity<?> saveRoute(@RequestBody Route route)
    {
        return new ResponseEntity<>(this.routeService.addRoute(route), HttpStatus.CREATED);
    }
	
	@PutMapping("update")
	public ResponseEntity<?> updateTodo(@RequestBody Route route) {
		return ResponseEntity.ok(this.routeService.updateRoute(route));
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteTodo(@PathVariable Long id) {
		this.routeService.deleteRoute(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("getAll")
    public ResponseEntity<?> getAll()
    {
        return new ResponseEntity<>(routeService.getAll(), HttpStatus.OK);
    }
	
	@GetMapping("getRouteById/{id}")
    public ResponseEntity<?> getRouteById(@PathVariable("id") Long id)
    {
        return new ResponseEntity<>(routeService.getRouteById(id), HttpStatus.OK);
    }
	
	@GetMapping("getStartEnd")
    public ResponseEntity<?> getStartEnd(String begin, String end)
    {
        return new ResponseEntity<>(this.routeService.findByRouteX(begin, end), HttpStatus.OK);
    }

}
