package com.example.app.models.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
    @NotEmpty(message = "Nome obbligatorio")
    @Size(min = 3, max = 30, message = "Il nome deve essere tra 3 e 30 caratteri")
    private String name;

    @NotEmpty(message = "Indirizzo email obbligatoria")
    @Email(message = "Formato email non valido")
    private String email;

    @NotEmpty(message = "Password obbligatoria")
    @Size(min = 8, max = 32, message = "La password deve avere tra gli 8 e i 32 caratteri")
    private String password;

    @NotEmpty(message = "Nome d'Arte obbligatorio")
    private String artName;

    public RegisterRequest(String name, String email, String password, String artName) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.artName = artName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getArtName() {
      return artName;
    }

    public void setArtName(String artName) {
      this.artName = artName;
    }
}
