package com.aaronj.stdiscm.p4.EnrollCourseNode;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;

@Configuration
public class JwtSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/enrollments")
                .hasAuthority("ROLE_STUDENT") // Require "role" to be "STUDENT"
                .requestMatchers(HttpMethod.POST, "/enrollments")
                .hasAuthority("ROLE_STUDENT") // Require "role" to be "STUDENT"
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2.jwt());
        return http.build();
    }
}

