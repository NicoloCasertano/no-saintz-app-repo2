package com.example.app.models.services;

import com.example.app.models.dtos.response.UserResponse;
import com.example.app.models.entities.Authority;
import com.example.app.models.entities.User;
import com.example.app.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AdminServiceImpl implements AdminService{
    private UserRepository userRepo;

    @Autowired
    public AdminServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        return StreamSupport.stream(userRepo.findAll().spliterator(), false)
                .map(this::convertToUserResponse).toList();
    }
    @Transactional
    @Override
    public UserResponse promoteToAdmin(int userId) {
      User user = userRepo.findById(userId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Utente non trovato"));

      boolean isAlreadyAdmin = user.getAuthorities().stream()
        .anyMatch(auth -> "ROLE_ADMIN".equals(auth.getAuthority()));

      if (isAlreadyAdmin) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'utente è già un Admin");
      }

      Set<Authority> updatedAuthorities = user.getAuthorities().stream()
        .filter(Authority.class::isInstance)
        .map(Authority.class::cast)
        .collect(Collectors.toSet());
      updatedAuthorities.add(new Authority("ROLE_ADMIN"));
      user.setAuthorities(updatedAuthorities.stream().toList());

      User savedUser = userRepo.save(user);

      return convertToUserResponse(savedUser);
    }

    @Transactional
    @Override
    public void deleteNonAdminUser(int userId) {
        Optional<User> user = userRepo.findById(userId);

        if (user.isEmpty() || user.get().getAuthorities().stream().anyMatch(authority -> "ROLE_ADMIN"
                .equals(authority.getAuthority()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "L'utente non esiste o è un Admin");
        }

        userRepo.delete(user.get());
    }

    private UserResponse convertToUserResponse(User user) {
        return new UserResponse(
            user.getUserId(),
            user.getUsername(),
            user.getPassword(),
            user.getEmail(),
            user.getArtName(),
            user.getAuthorities().stream().map(auth -> (Authority) auth).toList());
    }

}
