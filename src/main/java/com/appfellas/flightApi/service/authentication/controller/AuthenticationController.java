package com.appfellas.flightApi.service.authentication.controller;

import com.appfellas.flightApi.core.enums.Role;
import com.appfellas.flightApi.core.security.SecurityContextUtils;
import com.appfellas.flightApi.service.authentication.dto.input.AuthenticationInput;
import com.appfellas.flightApi.service.authentication.dto.response.AuthenticationResponse;
import com.appfellas.flightApi.service.authentication.service.AuthenticationService;
import com.appfellas.flightApi.service.user.entity.User;
import com.appfellas.flightApi.service.user.service.UserService;
import com.appfellas.flightApi.util.EntityMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/verify")
    public ResponseEntity<AuthenticationResponse> whoAmI(){
        String userId = SecurityContextUtils.getPrincipal(SecurityContextHolder.getContext().getAuthentication(), Role.USER);
        return ResponseEntity.ok().body(authenticationService.generateAuthenticationResponse(userService.findById(userId)));
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
