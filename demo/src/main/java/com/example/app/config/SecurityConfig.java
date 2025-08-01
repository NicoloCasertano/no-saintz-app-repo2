package com.example.app.config;

import com.example.app.models.repositories.UserRepository;
import com.example.app.models.services.JwtServiceImpl;
import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

  private final UserRepository userRepo;


  public SecurityConfig(UserRepository userRepo) {
    this.userRepo = userRepo;
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
  public UserDetailsService userDetailsService() {
    return username -> userRepo.findByEmail(username)
      .map(user -> org.springframework.security.core.userdetails.User.builder()
        .username(user.getEmail())
        .password(user.getPassword())
        .authorities("ROLE_EMPLOYEE")
        .build())
      .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService());
  }

  @Bean
  public AuthenticationEntryPoint authenticationEntryPoint() {
    return (request, response, ex) -> {
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.setContentType("application/json");
      response.setHeader("WWW-Authenticate", "");
      response.getWriter().write("{\"error\": \"Unauthorized access\"}");
    };
  }

  @Bean
  public CorsFilter corsFilter() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("http://localhost:4200");
    config.addAllowedHeader("*"); // oppure specifica: "Authorization", "Content-Type"
    config.addAllowedMethod("*"); // oppure specifica: "GET", "POST", etc.

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);

    return new CorsFilter(source);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http, JwtServiceImpl jwtService, UserDetailsService uds) throws Exception {
    JwtAuthenticationFilter jwtFilter = new JwtAuthenticationFilter(jwtService, uds);

    http
      .cors(cors -> {})
      .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint()))
      .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // JWT PRIMA
      .addFilterBefore(corsFilter(), JwtAuthenticationFilter.class)          // CORS DOPO JWT
      .authorizeHttpRequests(auth -> auth
        .requestMatchers("/api/authentications/log-in-area").permitAll()
        .requestMatchers("/api/authentications/register-area").permitAll()
        .requestMatchers(HttpMethod.GET, "/api/audios/**", "/api/works/**").permitAll()
        .requestMatchers(HttpMethod.POST, "/api/works/upload").hasAnyRole("EMPLOYEE", "ADMIN")
        .requestMatchers("/api/admins/**").hasRole("ADMIN")
        .requestMatchers("/api/users/**").permitAll()
        .anyRequest().permitAll()
      )
      .httpBasic(Customizer.withDefaults())
      .csrf(AbstractHttpConfigurer::disable);

    return http.build();
  }

  @Bean
  public CommonsRequestLoggingFilter requestLoggingFilter() {
    CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
    loggingFilter.setIncludeClientInfo(true);
    loggingFilter.setIncludeQueryString(true);
    loggingFilter.setIncludeHeaders(true);
    loggingFilter.setIncludePayload(false); // true se vuoi anche body
    return loggingFilter;
  }
}
