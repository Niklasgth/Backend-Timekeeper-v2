package com.tidsrapport.backend.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
          // Applicera CORS-regler på alla endpoints under /api/
          .addMapping("/api/**")
          // Tillåter frontend-apps ursprung
          .allowedOrigins("http://localhost:5173")
          // la in alla crud då de kan behövas, fick tips om options ockås då den i spring checkar interna operationer vid anrop.
          .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE");
          
    }
}
