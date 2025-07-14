package com.example.app.config;

import com.example.app.models.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final UserRepository userRepo;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(UserRepository userRepo, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userRepo = userRepo;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, ex) -> {
            response.setStatus(HttpStatus.UNAUTHORIZED.ordinal());
            response.setContentType("application/json");
            response.setHeader("WWW-Authenticate", "");
            response.getWriter().write("{\"error\": \"Unauthorized access\"}");
        };
    }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .cors(cors -> {})  // se hai configurato un CorsConfigurationSource
      .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint()))
      .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
      .authorizeHttpRequests(auth -> auth
        // 1) Endpoints di autenticazione aperti a tutti
        .requestMatchers("/api/authentications/**")
        .permitAll()

        // 2) GET pubbliche per works e audio
        .requestMatchers(HttpMethod.GET, "/api/audios/**", "/api/works/**")
        .permitAll()

        // 3) sia USER che ADMIN possono fare upload
        .requestMatchers(HttpMethod.POST, "/api/works/upload")
        .hasAnyRole("USER", "ADMIN")

        // 4) Solo ADMIN può gestire rotte /api/admins/**
        .requestMatchers("/api/admins/**")
        .hasRole("ADMIN")

        // 5) Rotte /api/users/** richiedono autenticazione (qualsiasi utente loggato)
        .requestMatchers("/api/users/**")
        .authenticated()

        // 6) Qualunque altra richiesta (es. frontend resources) è permessa
        .anyRequest().permitAll()
      );

    return http.build();
  }

}

