package com.example.app.models.dtos;

import com.example.app.models.entities.User;

public class UserDto {
    private Integer userId;
    private String userName;
    private String password;
    private String email;
    private String artName;

  public UserDto() {
  }

  public UserDto(Integer userId, String userName, String password, String email, String artName) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.artName = artName;
    }

    public User toUser() {
        return new User(userId, userName, password, email, artName);
    }

    static public UserDto toDto(User u) {
      UserDto dto = new UserDto();
      dto.setUserId(u.getUserId());
      dto.setUserName(u.getUsername());
      dto.setEmail(u.getEmail());
      dto.setArtName(u.getArtName());
      return dto;
    }

    public static UserDto fromEntity(User u) {
      var dto = new UserDto();
      dto.setUserId(u.getUserId());
      dto.setUserName(u.getUsername());
      dto.setPassword(u.getPassword());
      dto.setArtName(u.getArtName());
      dto.setEmail(u.getEmail());
      return dto;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
