package com.example.app.models.services;

import com.example.app.models.dtos.UserDto;
import com.example.app.models.dtos.request.PasswordUpdateRequest;
import com.example.app.models.entities.Authority;
import com.example.app.models.entities.User;
import com.example.app.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class JpaUserService implements UserService {
    private UserRepository userRepo;

    @Autowired
    public JpaUserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Optional<User> findUserById(int id) {
        return userRepo.findById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User updateUser(User user) {
        return saveUser(user);
    }

    @Override
    public boolean deleteUser(int id) {
        userRepo.deleteById(id);
        return true;
    }

    @Override
    public Optional<User> findByArtName(String artName) {
      return userRepo.findByArtName(artName);
    }

    @Override
    public Optional<UserDto> findDtoById(Integer id) {
    return userRepo.findById(id).map(UserDto::toDto);
  }

    @Override
    public UserDetails loadUserByUsername(String email) {
      return userRepo.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
