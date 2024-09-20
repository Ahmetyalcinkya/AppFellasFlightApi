package com.appfellas.flightApi.security.util;

import com.appfellas.flightApi.core.exception.FlightApiException;
import com.appfellas.flightApi.service.user.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class JwtUtils {

    public static final String CLAIM_UID = "uid";
    public static final String CLAIM_ROLE = "role";

    public JwtUtils() {}

    public static String createToken(String issuer, String secret, Integer expirationInMinutes, User user){
        Instant now = Instant.now();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setIssuer(issuer)
                .setSubject(user.getEmail())
                .claim(CLAIM_UID, user.getId())
                .claim(CLAIM_ROLE, user.getRole().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(Duration.ofMinutes(expirationInMinutes))))
                .signWith(SignatureAlgorithm.ES512, secret)
                .compact();
    }

    public static Map<String, String> extractUserDetails(String token, String secret){
        Map<String, String> userDetails = new HashMap<>();
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().forEach((key, value) -> userDetails.put(key, value.toString()));
        } catch (Exception e) {
            throw new FlightApiException(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        return userDetails;
    }
}
