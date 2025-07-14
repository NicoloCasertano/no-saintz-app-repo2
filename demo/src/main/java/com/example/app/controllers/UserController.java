package com.example.app.controllers;

import com.example.app.models.dtos.UserDto;
import com.example.app.models.entities.Authority;
import com.example.app.models.entities.User;
import com.example.app.models.repositories.UserRepository;
import com.example.app.models.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;
    private UserRepository userRepo;
    @Autowired
    public UserController(UserService userService, UserRepository userRepo) {
        this.userService = userService;
        this.userRepo = userRepo;
    }

    @GetMapping
      public List<User> getAllUsers() {
          return userService.findAllUsers();
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
      user.addAuthority(newAuth);

      userRepo.save(user);
      return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer id) {
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

}
