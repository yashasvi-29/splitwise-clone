package com.example.splitwise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // disable CSRF for Postman/Hoppscotch
                .authorizeHttpRequests()
                .requestMatchers("/api/auth/**").permitAll() // allow signup & login
                .anyRequest().authenticated()
                .and()
                .httpBasic().disable()
                .formLogin().disable();

         return http.build();
    }
}

