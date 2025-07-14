package com.example.app.config;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtKeyConfig {

  @Value("${spring.jwt.secret}")
  private String base64Secret;

  @Bean
  public SecretKey jwtSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
