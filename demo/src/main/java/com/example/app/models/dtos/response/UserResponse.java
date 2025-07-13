package com.example.app.models.dtos.response;

import com.example.app.models.entities.Authority;

import java.util.List;

public class UserResponse {
    private Integer userId;
    private String userName;

    private String email;
    private String artName;
    private List<Authority> authorities;

    public UserResponse(Integer userId, String userName, String email, String artName, List<Authority> authorities) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.artName = artName;
        this.authorities = authorities;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getArtName() {
        return artName;
    }

    public void setArtName(String artName) {
        this.artName = artName;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}
