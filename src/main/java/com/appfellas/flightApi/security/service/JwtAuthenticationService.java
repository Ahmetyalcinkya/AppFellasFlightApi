package com.appfellas.flightApi.security.service;

import com.appfellas.flightApi.core.enums.Role;
import com.appfellas.flightApi.security.token.JwtAuthenticationToken;
import com.appfellas.flightApi.security.util.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JwtAuthenticationService {

    private final String secret;

    public JwtAuthenticationService(@Value("${app.security.jwt.secret}") String secret) {
        this.secret = secret;
    }

    public JwtAuthenticationToken authenticate(JwtAuthenticationToken jwtAuthenticationToken){
        Map<String, String> details = JwtUtils.extractUserDetails(jwtAuthenticationToken.getToken(), secret);
        return new JwtAuthenticationToken(String.valueOf(details.get(JwtUtils.CLAIM_UID)), Role.valueOf(details.get(JwtUtils.CLAIM_ROLE)));
    }
}
