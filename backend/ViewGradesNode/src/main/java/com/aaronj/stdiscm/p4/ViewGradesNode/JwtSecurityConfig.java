package com.aaronj.stdiscm.p4.ViewGradesNode;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;

@Configuration
public class JwtSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .requestMatchers(HttpMethod.GET, "/grades")
            .hasAuthority("SCOPE_product.read")
            .and()
            .oauth2ResourceServer()
            .jwt();
        return http.build();
    }
    
}