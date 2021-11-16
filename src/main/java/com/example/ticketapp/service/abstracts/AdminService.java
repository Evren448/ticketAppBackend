package com.example.ticketapp.service.abstracts;

import java.util.List;

import com.example.ticketapp.dto.UserWithTicketDetailsDto;
import com.example.ticketapp.entity.User;

public interface AdminService {
	User saveUser(User user);
	User updateUser(User user);
	void deleteUserById(Long id);
	
	List<User> getUsers();
	User getUserById(Long id);
	List<UserWithTicketDetailsDto> getUserDetails();
}
