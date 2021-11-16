package com.example.ticketapp.controller;

import java.time.LocalDateTime;
import java.util.Date;

import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticketapp.entity.Role;
import com.example.ticketapp.entity.User;
import com.example.ticketapp.repository.UserRepository;
import com.example.ticketapp.service.abstracts.AuthService;
import com.example.ticketapp.service.abstracts.UserService;

@RestController
@RequestMapping("/api/authentication")
public class AuthController {
	
	@Autowired
    private AuthService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping("sign-up") //api/authentication/sign-up
    public ResponseEntity<?> signUp(@RequestBody User user)
    {
        if (userService.findByUsername(user.getUsername()).isPresent())
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping("sign-in")//api/authentication/sign-in
    public ResponseEntity<?> signIn(@RequestBody User user)
    {
        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
    }
}
