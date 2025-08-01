package com.example.app.models.services;

import com.example.app.models.dtos.request.AuthenticationRequest;
import com.example.app.models.dtos.request.RegisterRequest;
import com.example.app.models.dtos.response.AuthenticationResponse;
import com.example.app.models.entities.Authority;
import com.example.app.models.entities.User;
import com.example.app.models.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    @Transactional
    public AuthenticationResponse register(String userName, String email, String rawPassword, String artName) throws Exception {
      if (userRepository.findByEmail(email).isPresent()) {
        throw new Exception("User already exists");
      }

      User user = new User();
      user.setUserName(userName);
      user.setEmail(email);
      user.setPassword(passwordEncoder.encode(rawPassword));
      user.setArtName(artName);

      User savedUser = userRepository.save(user);
      userRepository.flush();
      System.out.println("Saved user artName: " + savedUser.getArtName());

      String token = jwtService.generateToken(new HashMap<>(), savedUser);

      return new AuthenticationResponse(
        token,
        savedUser.getUsername(),
        savedUser.getAuthorities().stream()
          .map(GrantedAuthority::getAuthority)
          .collect(Collectors.toList()),
        savedUser.getArtName()
      );
    }

    @Override
    @Transactional(readOnly = true)
    public AuthenticationResponse login(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        String jwtToken = jwtService.generateToken(new HashMap<>(), user);

        return new AuthenticationResponse(jwtToken, user.getUsername(), user.getAuthorities().stream()
          .map(GrantedAuthority::getAuthority).collect(Collectors.toList()), user.getArtName());
    }


  private boolean isEmailTaken(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private User buildNewUser(RegisterRequest input) {
        User user = new User();
        user.setUserId(0);
        user.setUserName(input.getName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setAuthorities(initialAuthority().stream().toList());
        return user;
    }

    private Set<Authority> initialAuthority() {
        boolean isFirstUser = userRepository.count() == 0;
      Set<Authority> authorities = new HashSet<>();
        authorities.add(new Authority("ROLE_EMPLOYEE"));
        if (isFirstUser) {
            authorities.add(new Authority("ROLE_ADMIN"));
        }
        return authorities;
    }
}
