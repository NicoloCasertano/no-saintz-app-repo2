package com.example.app.models.services;

import com.example.app.models.dtos.UserDto;
import com.example.app.models.dtos.request.PasswordUpdateRequest;
import com.example.app.models.entities.User;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserService  {
    Optional<User> findUserById(int id);

    List<User> findAllUsers();

    User saveUser(User user);

    User updateUser(User user);

    boolean deleteUser(int id);

    Optional<User> findByArtName(String artName);

    Optional<UserDto> findDtoById(Integer id);

    UserDetails loadUserByUsername(String email);

}
