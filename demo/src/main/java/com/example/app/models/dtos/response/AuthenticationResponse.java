package com.example.app.models.dtos.response;

import java.util.List;

public class AuthenticationResponse {
    private String token;
    private String username;
    private List<String> authorities;
    private String artName;
    public AuthenticationResponse(String token, String username, List<String> authorities, String artName) {
        this.token = token;
        this.username = username;
        this.authorities = authorities;
        this.artName = artName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(List<String> authorities) {
    this.authorities = authorities;
  }

  public String getArtName() {
    return artName;
  }

  public void setArtName(String artName) {
    this.artName = artName;
  }
}
