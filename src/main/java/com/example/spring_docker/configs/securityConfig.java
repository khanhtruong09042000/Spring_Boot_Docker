package com.example.spring_docker.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// @EnableWebSecurity
public class securityConfig {
    // public SecurityFilterChain securityFilterChain(HttpSecurity http){
    //     return http.csrf(customizer -> customizer.disable())
    //                 .authorizeHttpRequests(req -> req.requestMatchers("/auth/**").permitAll()
    //                                                     .anyRequest().authenticated())
    //                 .httpBasic(Customizer.withDefaults())
    //                 .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authenticationProvider(null).addFilterBefore(null, UsernamePasswordAuthenticationFilter.class).build();
    // }
}
