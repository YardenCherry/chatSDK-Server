package com.example.chatsdk.controllers;

import com.example.chatsdk.models.User;
import com.example.chatsdk.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/loadUser")
    public ResponseEntity<?> getUserByUsername(@RequestParam String userId) {
        User user = userService.loadUser(userId);
        if (user == null) {
            return ResponseEntity.status(401).body("Invalid user id");
        }
        return ResponseEntity.ok(user);
    }


    @PutMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        if (allUsers.isEmpty()) {
            return ResponseEntity.status(204).body("No users found"); // HTTP 204 No Content
        }
        return ResponseEntity.ok(allUsers); // HTTP 200 OK
    }


    @PutMapping("/logout")
    public ResponseEntity<?> logoutUser(@RequestParam String id) {
        try {
            User user = userService.logoutUser(id);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("User not found: " + e.getMessage());
        }
    }

}
