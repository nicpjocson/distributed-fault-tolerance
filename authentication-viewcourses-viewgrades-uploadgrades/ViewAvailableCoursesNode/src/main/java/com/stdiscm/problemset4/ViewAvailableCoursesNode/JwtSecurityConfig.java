package com.stdiscm.problemset4.ViewAvailableCoursesNode;

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
            .requestMatchers(HttpMethod.GET, "/products")
            .hasAuthority("SCOPE_product.read")
            .requestMatchers(HttpMethod.POST, "/product")
            .hasAuthority("SCOPE_product.write")
            .and()
            .oauth2ResourceServer()
            .jwt();
        return http.build();
    }
    
}

