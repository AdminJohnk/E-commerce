package com.example.productmanagement.controllers;

import com.example.productmanagement.dtos.user.UserDTO;
import com.example.productmanagement.entities.User;
import com.example.productmanagement.services.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO dto, Principal principal) {
        return new ResponseEntity<>(userService.create(dto, principal), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody UserDTO dto, Principal principal) {
        return new ResponseEntity<>(userService.update(id, dto, principal), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable String id) {
        return new ResponseEntity<>(userService.delete(id), HttpStatus.OK);
    }
}

