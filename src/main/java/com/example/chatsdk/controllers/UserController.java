package com.example.chatsdk.controllers;

import com.example.chatsdk.models.User;
import com.example.chatsdk.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestParam String username, @RequestParam String passwordHash, @RequestParam(required = false) String avatarUrl) {
        return userService.registerUser(username, passwordHash, avatarUrl);
    }

    @GetMapping("/login")
    public ResponseEntity<?> getUserByUsername(@RequestParam String username, @RequestParam String password) {
        User user = userService.authenticateUser(username, password);
        if (user == null) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}/status")
    public User updateStatus(@PathVariable Long id, @RequestParam String status) {
        return userService.updateUserStatus(id, status);
    }
}
