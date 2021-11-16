package com.example.ticketapp.service.abstracts;

import com.example.ticketapp.entity.User;

public interface AuthService {
	User signInAndReturnJWT(User signInRequest);
}
