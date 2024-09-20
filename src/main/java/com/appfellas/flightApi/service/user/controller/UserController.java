package com.appfellas.flightApi.service.user.controller;

import com.appfellas.flightApi.core.enums.Role;
import com.appfellas.flightApi.core.security.SecurityContextUtils;
import com.appfellas.flightApi.service.user.dto.input.UserInput;
import com.appfellas.flightApi.service.user.dto.response.UserResponse;
import com.appfellas.flightApi.service.user.service.UserService;
import com.appfellas.flightApi.util.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok().body(userService.findAll().stream().map(EntityMapper::user).toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
        return ResponseEntity.ok().body(EntityMapper.user(userService.findById(id)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<UserResponse> getUserByEmail(@RequestParam(name = "email") String email) {
        return ResponseEntity.ok().body(EntityMapper.user(userService.findByEmail(email)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<String> saveUser(@RequestBody UserInput input) {
        userService.save(input);
        return ResponseEntity.ok().body("");
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody UserInput input) {
        userService.update(SecurityContextUtils.getPrincipal(SecurityContextHolder.getContext().getAuthentication(), Role.USER), input);
        return ResponseEntity.ok().body("");
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update-flights")
    public ResponseEntity<String> addFlightToUser(@RequestBody List<String> flights) {
        return ResponseEntity.ok().body(userService.addFlightToUser(SecurityContextUtils.getPrincipal(SecurityContextHolder.getContext().getAuthentication(), Role.USER), flights));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.ok().body("");
    }
}
