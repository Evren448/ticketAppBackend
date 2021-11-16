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

import com.example.ticketapp.entity.User;
import com.example.ticketapp.service.abstracts.AdminService;
import com.example.ticketapp.service.abstracts.TicketService;
import com.example.ticketapp.service.abstracts.UserService;
import com.example.ticketapp.service.abstracts.VehicleService;


@RestController
@RequestMapping("api/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private VehicleService vehicleService;
	
    @Autowired
    private UserService userService;
	
	@PostMapping("/add")
    public ResponseEntity<?> saveUser(@RequestBody User user)
    {
		if (userService.findByUsername(user.getUsername()).isPresent())
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(adminService.saveUser(user), HttpStatus.CREATED);
    }
	
	@PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user)
    {
        return new ResponseEntity<>(adminService.updateUser(user), HttpStatus.OK);
    }
	
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id)
    {
		this.adminService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@GetMapping("getUser/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id)
    {
        return new ResponseEntity<>(this.adminService.getUserById(id), HttpStatus.OK);
    }
	
	@GetMapping("/getUsers")
	public ResponseEntity<?> getUsers(){
		return ResponseEntity.ok(this.adminService.getUsers());
	}
	
	@GetMapping("/getTickets")
	public ResponseEntity<?> getTickets(){
		return ResponseEntity.ok(this.ticketService.getTickets());
	}
	
	@GetMapping("/getVehicles")
	public ResponseEntity<?> getVehicles(){
		return ResponseEntity.ok(this.vehicleService.getVehicles());
	}
	
	@GetMapping("/getUsersDetails")
	public ResponseEntity<?> getUsersDetails(){
		return ResponseEntity.ok(this.adminService.getUserDetails());
	}
	
}
