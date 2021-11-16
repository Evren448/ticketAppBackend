package com.example.ticketapp.service.concretes;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ticketapp.entity.Role;
import com.example.ticketapp.entity.User;
import com.example.ticketapp.repository.UserRepository;
import com.example.ticketapp.service.abstracts.UserService;

@Service
public class UserManager implements UserService{
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ADMIN);
        user.setCreateTime(LocalDateTime.now());

        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional //TransactionalRequired when executing an update/delete query.
    public void makeAdmin(String username)
    {
    	User user = this.findByUsername(username).orElse(null);
    	if(user != null) {
    		user.setRole(Role.ADMIN);
    	}
        //userRepository.updateUserRole(username, Role.ADMIN);
    }
}
