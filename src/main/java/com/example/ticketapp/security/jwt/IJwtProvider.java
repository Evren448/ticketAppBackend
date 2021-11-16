package com.example.ticketapp.security.jwt;

import org.springframework.security.core.Authentication;

import com.example.ticketapp.security.UserPrincipal;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sa
 * @date 3.07.2021
 * @time 19:15
 */
public interface IJwtProvider
{
    String generateToken(UserPrincipal auth);

    Authentication getAuthentication(HttpServletRequest request);

    boolean validateToken(HttpServletRequest request);
}
