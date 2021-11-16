package com.example.ticketapp.service.abstracts;

import java.util.Optional;

import com.example.ticketapp.entity.User;

public interface UserService {
    User saveUser(User user);

    Optional<User> findByUsername(String username);

    void makeAdmin(String username);
}
