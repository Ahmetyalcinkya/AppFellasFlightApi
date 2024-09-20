package com.appfellas.flightApi.core.security;

import com.appfellas.flightApi.core.enums.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.function.Predicate;

public final class SecurityContextUtils {

    private SecurityContextUtils() {
    }

    public static boolean containsRole(Authentication authentication, Role... role) {
        return authentication.getAuthorities().stream().anyMatch((Predicate<GrantedAuthority>) grantedAuthority -> Arrays.stream(role).anyMatch(current -> String.format("ROLE_%s", current.name()).equals(grantedAuthority.getAuthority())));
    }

    public static String getPrincipal(Authentication authentication, Role... role){
        return SecurityContextUtils.containsRole(authentication, role) ? (String) authentication.getPrincipal() : null;
    }
}
