package com.appfellas.flightApi.service.authentication.service;

import com.appfellas.flightApi.core.exception.FlightApiException;
import com.appfellas.flightApi.security.util.JwtUtils;
import com.appfellas.flightApi.service.authentication.dto.response.AuthenticationResponse;
import com.appfellas.flightApi.service.user.entity.User;
import com.appfellas.flightApi.service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final String jwtIssuer;
    private final String jwtSecret;
    private final Integer jwtExpirationInMinutes;
    @Autowired
    public AuthenticationService(UserService userService, PasswordEncoder passwordEncoder, @Value("${app.security.jwt.issuer}") String jwtIssuer, @Value("${app.security.jwt.secret}") String jwtSecret, @Value("${app.security.jwt.expiration-in-minutes}") Integer jwtExpirationInMinutes) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtIssuer = jwtIssuer;
        this.jwtSecret = jwtSecret;
        this.jwtExpirationInMinutes = jwtExpirationInMinutes;
    }

    public AuthenticationResponse signIn(String email, String password){
        User user = userService.findByEmail(email);
        if (Boolean.FALSE.equals(passwordEncoder.matches(password, user.getPassword()))) throw new FlightApiException("Incorrect username or password", HttpStatus.BAD_REQUEST);
        return generateAuthenticationResponse(user);
    }

    public boolean signOut(String id){
        User user = userService.findById(id);
        SecurityContextHolder.clearContext();
        return user != null;
    }

    public AuthenticationResponse generateAuthenticationResponse(User user){
        AuthenticationResponse response = new AuthenticationResponse();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().name());
        response.setToken(JwtUtils.createToken(jwtIssuer, jwtSecret, jwtExpirationInMinutes, user));
        return response;
    }
}
