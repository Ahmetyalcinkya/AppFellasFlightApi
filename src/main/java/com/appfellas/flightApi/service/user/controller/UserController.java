package com.appfellas.flightApi.service.user.controller;

import com.appfellas.flightApi.service.user.dto.input.UserInput;
import com.appfellas.flightApi.service.user.dto.response.UserResponse;
import com.appfellas.flightApi.service.user.service.UserService;
import com.appfellas.flightApi.util.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok().body(userService.findAll().stream().map(EntityMapper::user).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
        return ResponseEntity.ok().body(EntityMapper.user(userService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<UserResponse> getUserByEmail(@RequestParam(name = "email") String email) {
        return ResponseEntity.ok().body(EntityMapper.user(userService.findByEmail(email)));
    }

    @PostMapping("/create")
    public ResponseEntity<String> saveUser(@RequestBody UserInput input) {
        userService.save(input);
        return ResponseEntity.ok().body("");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody UserInput input) {
        userService.update(id, input);
        return ResponseEntity.ok().body("");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        userService.delete(id);
        return ResponseEntity.ok().body("");
    }
}
