package com.example.app.models.services;

import com.example.app.models.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {
    String extractUsername(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
    String generateToken(Map<String, Object> claims, User userDetails);
}
