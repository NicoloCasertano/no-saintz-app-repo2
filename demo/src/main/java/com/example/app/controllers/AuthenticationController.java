package com.example.app.controllers;

import com.example.app.models.dtos.request.AuthenticationRequest;
import com.example.app.models.dtos.request.RegisterRequest;
import com.example.app.models.dtos.response.AuthenticationResponse;
import com.example.app.models.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/authentications")
public class AuthenticationController {
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping("/register-area")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) throws Exception {
        AuthenticationResponse response = authenticationService.register(
          request.getName(),
          request.getEmail(),
          request.getPassword(),
          request.getArtName());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ResponseStatus(HttpStatus.OK) // fa il login del utente
    @PostMapping("/log-in-area")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest authRequest) throws Exception{
      AuthenticationResponse response = authenticationService.login(authRequest);
      return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ControllerAdvice
    public class GlobalExceptionHandler {
      @ExceptionHandler(Exception.class)
      public ResponseEntity<String> handleAllExceptions(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Errore interno: " + ex.getMessage());
      }
    }
}
