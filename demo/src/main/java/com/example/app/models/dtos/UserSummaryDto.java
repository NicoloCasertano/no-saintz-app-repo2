package com.example.app.models.dtos;

import com.example.app.models.entities.User;

public class UserSummaryDto {
  private Integer userId;
  private String userName;
  private String email;
  private String artName;

  public UserSummaryDto(Integer userId, String userName, String email, String artName) {
    this.userId = userId;
    this.userName = userName;
    this.email = email;
    this.artName = artName;
  }

  public static UserSummaryDto toDto(User u) {
    return new UserSummaryDto(u.getUserId(), u.getUsername(), u.getEmail(), u.getArtName());
  }

  public User toUser() {
    User u = new User();
    u.setUserId(this.userId);
    u.setUserName(this.userName);
    u.setEmail(this.email);
    u.setArtName(this.artName);
    return u;
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


}
