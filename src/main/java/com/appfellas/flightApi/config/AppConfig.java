package com.appfellas.flightApi.config;

import com.appfellas.flightApi.core.enums.Role;
import com.appfellas.flightApi.service.user.entity.User;
import com.appfellas.flightApi.service.user.repository.UserRepository;
import com.appfellas.flightApi.service.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class AppConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    public CommandLineRunner createAdmin(@Autowired UserService userService, @Autowired UserRepository userRepository){
        return args -> {
            User found = userService.findByEmail("ahmetcan.yalcinkaya55@gmail.com");
            if(found == null) {
                User user = new User();
                user.setFirstName("Ahmet Can");
                user.setLastName("Yalçınkaya");
                user.setEmail("ahmetcan.yalcinkaya55@gmail.com");
                user.setPassword("Ahmet123."); // TODO : passwordEncoder -> encode the password after the security;
                user.setCreatedDateTime(LocalDateTime.now());
                user.setRole(Role.ADMIN);
                userRepository.save(user);
            } else {
                LOGGER.info("Admin account created -> Email: {} / Password: Ahmet123.", found.getEmail());
            }
        };
    }
}
