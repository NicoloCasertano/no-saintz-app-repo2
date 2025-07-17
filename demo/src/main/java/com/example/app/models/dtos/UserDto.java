package com.example.app.models.dtos;

import com.example.app.models.entities.Authority;
import com.example.app.models.entities.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDto {
    private Integer userId;
    private String userName;
    private String password;
    private String email;
    private String artName;
    private List<WorkDto> works;


  public UserDto() {
  }

  public UserDto(Integer userId, String userName, String password, String email, String artName, List<WorkDto> works) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.artName = artName;
        this.works = works;
    }

    public User toUser() {
        return new User(userId, userName, password, email, artName);
    }

    static public UserDto toDto(User u) {
      UserDto dto = new UserDto();
      dto.setUserId(u.getUserId());
      dto.setUserName(u.getUsername());
      dto.setPassword(null);
      dto.setEmail(u.getEmail());
      dto.setArtName(u.getArtName());
      dto.setWorks(u.getWorks().stream()
        .map(WorkDto::toDto).toList()
      );
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

    public List<WorkDto> getWorks() {
      return works;
    }

    public void setWorks(List<WorkDto> works) {
      this.works = works;
    }

}
