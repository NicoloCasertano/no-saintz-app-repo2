package com.example.app.models.services;

import com.example.app.models.dtos.request.AuthenticationRequest;
import com.example.app.models.dtos.request.RegisterRequest;
import com.example.app.models.dtos.response.AuthenticationResponse;
import com.example.app.models.entities.Authority;


public interface AuthenticationService {
    AuthenticationResponse register(String userName, String email, String password, String artName) throws Exception;
    AuthenticationResponse login(AuthenticationRequest request);
}
