package com.example.app.models.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class AuthenticationRequest {
    @NotEmpty(message = "Email obbligatoria")
    @Email(message = "Formato dell'email non valido")
    private String email;

    @NotEmpty(message = "Password obbligatoria")
    @Size(min = 8, max = 32, message = "Password deve contenere tra gli 8 e i 32 caratteri")
    private String password;

    public AuthenticationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
