package com.example.ticketapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/echo")
public class EchoController {
	
	@GetMapping
	public String echo() {
		return "echo";
	}
}
