package com.aaronj.stdiscm.p4.UploadGradesNode;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.Collections;

import org.springframework.context.annotation.Bean;

@Configuration
public class JwtSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/grades").hasRole("TEACHER") // Restrict access to 'TEACHER' role
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter())
            );
        return http.build();
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
        authenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
            // Extract the "role" claim and map it to a Spring Security authority
            String role = jwt.getClaimAsString("role");
            return role != null ? List.of(() -> "ROLE_" + role) : List.of();
        });
        return authenticationConverter;
    }
}