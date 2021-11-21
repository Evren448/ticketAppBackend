package com.example.ticketapp.advice;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TicketControllerAdvice {
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> handleException(Exception exception) {
		return new ResponseEntity<>("Hata ile karsilasildi", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<String> IllegalArgumentHandleException(IllegalArgumentException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
