package com.example.spring_docker.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.spring_docker.repositories.userRepository;

@Configuration
public class applicationConfig {
    @Autowired
    private userRepository userRepo;

    @Bean
    UserDetailsService userDetailsService(){
        return username -> userRepo.findByEmail(username);
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider aDaoAuthenticationProvider = new DaoAuthenticationProvider();
        aDaoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        aDaoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return aDaoAuthenticationProvider;
    }
}
