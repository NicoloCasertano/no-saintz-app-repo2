package com.example.app.config;

import com.example.app.models.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class AppConfig {
    private final UserRepository userRepo;

    public AppConfig(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userName -> (org.springframework.security.core.userdetails.UserDetails) userRepo.findByEmail(userName).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
