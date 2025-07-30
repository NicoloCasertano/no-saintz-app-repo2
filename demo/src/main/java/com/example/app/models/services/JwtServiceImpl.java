package com.example.app.models.services;

import com.example.app.models.entities.Authority;
import com.example.app.models.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    @Autowired
    private SecretKey jwtSigningKey;

    @Value("${spring.jwt.expiration}")
    private long JWT_EXPIRATION;

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
      Claims claims = Jwts
        .parserBuilder()
        .setSigningKey(jwtSigningKey)
        .build()
        .parseClaimsJws(token)
        .getBody();
      return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts
          .parserBuilder()
          .setSigningKey(jwtSigningKey)
          .build()
          .parseClaimsJws(token)
          .getBody();
    }
    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String email = extractUsername(token); // sub = email
        return (email.equals(userDetails.getUsername()) || email.equals(((User) userDetails).getEmail()))
          && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    @Override
    public String generateToken(Map<String, Object> claims, User userDetails) {
        claims.put("userId", userDetails.getUserId());
        claims.put("userName", userDetails.getUsername());
        claims.put("password", null);
        claims.put("email", userDetails.getEmail());
        claims.put("artName", userDetails.getArtName());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(jwtSigningKey, SignatureAlgorithm.HS256)
                .compact();
    }

//    private Key getSigningKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
}
