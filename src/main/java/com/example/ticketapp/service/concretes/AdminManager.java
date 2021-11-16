package com.example.ticketapp.service.concretes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ticketapp.dto.UserWithTicketDetailsDto;
import com.example.ticketapp.entity.Role;
import com.example.ticketapp.entity.Ticket;
import com.example.ticketapp.entity.User;
import com.example.ticketapp.repository.TicketRepository;
import com.example.ticketapp.repository.UserRepository;
import com.example.ticketapp.service.abstracts.AdminService;

@Service
public class AdminManager implements AdminService{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public User saveUser(User user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateTime(LocalDateTime.now());

        return userRepository.save(user);
	}
	
	@Override
	public User updateUser(User user) {
		
		//user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateTime(LocalDateTime.now());
        user.setId(user.getId());

        return userRepository.save(user);
	}

	@Override
	public void deleteUserById(Long id) {
		this.userRepository.deleteById(id);
	}

	@Override
	public List<User> getUsers() {
		return this.userRepository.findAll();
	}

	@Override
	public List<UserWithTicketDetailsDto> getUserDetails() {

		
		List<Ticket> ticketList = this.ticketRepository.findAll();
		List<UserWithTicketDetailsDto> dtoList = new ArrayList<UserWithTicketDetailsDto>();
		
		for (Ticket ticket : ticketList) {
			UserWithTicketDetailsDto dto = new UserWithTicketDetailsDto();
			
			dto.setUserId(ticket.getUser().getId());
			dto.setTicketId(ticket.getId());
			dto.setRouteId(ticket.getRoute().getId());
			dto.setVehicleId(ticket.getVehicle().getId());
			
			dtoList.add(dto);
		}
		
		return dtoList;
		
	}

	@Override
	public User getUserById(Long id) {
		return this.userRepository.findById(id).orElse(null);
	}
	
	
}
