package com.appfellas.flightApi.security.token;

import com.appfellas.flightApi.core.enums.Role;
import com.appfellas.flightApi.core.exception.FlightApiException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;

public class JwtAuthenticationToken implements Authentication {

    private String token;
    private String principal;
    private Role role;

    private final boolean authenticated;

    public JwtAuthenticationToken(String token) {
        this.token = token;
        this.authenticated = true;
    }

    public JwtAuthenticationToken(String principal, Role role) {
        this.principal = principal;
        this.role = role;
        this.authenticated = true;
    }

    public String getToken() {
        return token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority(String.format("ROLE_%s", role.name())));
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new FlightApiException("JwtAuthenticationToken does not support setAuthenticated method", HttpStatus.BAD_REQUEST);
    }

    @Override
    public String getName() {
        return StringUtils.EMPTY;
    }
}
