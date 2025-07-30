package com.example.app.controllers;

import com.example.app.models.dtos.UserDto;
import com.example.app.models.entities.Authority;
import com.example.app.models.entities.User;
import com.example.app.models.repositories.UserRepository;
import com.example.app.models.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
//@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;
    private UserRepository userRepo;
    @Autowired
    public UserController(UserService userService, UserRepository userRepo) {
        this.userService = userService;
        this.userRepo = userRepo;
    }

    @GetMapping("/all")
      public List<UserDto> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "20") int size) {
          return userService.findAllUsers().stream().map(UserDto::toDto).collect(Collectors.toList());
      }

    @PutMapping("/{id}/role")
    public ResponseEntity<?> updateRole(
      @PathVariable Integer id,
      @RequestParam("role") String newRoleName
    ) {
      User user = userRepo.findById(id)
        .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));

      String roleString = "ROLE_" + newRoleName.toUpperCase();
      Authority newAuth = new Authority(roleString);

      user.clearAuthorities();
      user.addAuthority(newAuth.toString());

      userRepo.save(user);
      return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id, Principal principal) {
      System.out.println("Principal: " + principal);
      System.out.println("Fetching user with ID: " + id);
      var userOpt = userService.findUserById(id);

      if (userOpt.isEmpty()) {
        System.out.println("User not found");
        return ResponseEntity.status(NOT_FOUND).build();
      }

      var dto = UserDto.toDto(userOpt.get());
      System.out.println("User found: " + dto.getUserName());
      return ResponseEntity.ok(dto);
    }

    @GetMapping
    public Page<User> getUsers(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "20") int size
    ) {
      return (Page<User>) userService.findAllUsers();
    }
}
