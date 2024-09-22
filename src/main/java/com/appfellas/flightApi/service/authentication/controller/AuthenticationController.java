package com.appfellas.flightApi.service.authentication.controller;

import com.appfellas.flightApi.core.security.SecurityContextUtils;
import com.appfellas.flightApi.service.authentication.dto.input.AuthenticationInput;
import com.appfellas.flightApi.service.authentication.dto.response.AuthenticationResponse;
import com.appfellas.flightApi.service.authentication.service.AuthenticationService;
import com.appfellas.flightApi.service.user.entity.User;
import com.appfellas.flightApi.service.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationInput input) {
        return authenticationService.signIn(input.getEmail(), input.getPassword());
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/logout")
    public boolean logout(HttpServletRequest request) {
        String remoteUserId = request.getRemoteUser();
        User user = userService.findById(remoteUserId);
        return authenticationService.signOut(SecurityContextUtils.getPrincipal(SecurityContextHolder.getContext().getAuthentication(), user.getRole()));
    }
}
