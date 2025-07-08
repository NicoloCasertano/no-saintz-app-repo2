package com.example.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Value("${frontend.url}")
  private String frontendUrl;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/api/authentications/register-area")
      .allowedOriginPatterns(frontendUrl)
      .allowedMethods("GET", "POST", "OPTIONS")
      .allowedHeaders("Authorization", "Content-Type", "X-Requested-With")
      .exposedHeaders("Authorization")
      .allowCredentials(true)
      .maxAge(3600);

    registry.addMapping("/api/authentications/log-in-area")
      .allowedOriginPatterns(frontendUrl)
      .allowedMethods("GET", "POST", "OPTIONS")
      .allowedHeaders("Authorization", "Content-Type", "X-Requested-With")
      .exposedHeaders("Authorization")
      .allowCredentials(true)
      .maxAge(3600);

    registry.addMapping("/api/**")
      .allowedOriginPatterns(frontendUrl)
      .allowedMethods("GET", "POST", "OPTIONS")
      .allowedHeaders("Authorization", "Content-Type", "X-Requested-With")
      .exposedHeaders("Authorization")
      .allowCredentials(true)
      .maxAge(3600);
  }

}
