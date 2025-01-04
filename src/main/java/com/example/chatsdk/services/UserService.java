package com.example.chatsdk.services;

import com.example.chatsdk.models.User;
import com.example.chatsdk.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(String username, String passwordHash, String avatarUrl) {
        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(passwordHash);
        user.setAvatarUrl(avatarUrl);
        user.setStatus("offline");
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElse(null);
    }

    public User findById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElse(null);
    }

    public User updateUserStatus(Long userId, String status) {
        User user = findById(userId);
        if (user != null) {
            user.setStatus(status);
            return userRepository.save(user);
        }
        return null;
    }

    public User authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return null;
        }

        if (!user.getPasswordHash().equals(password)) {
            return null;
        }

        user.setStatus("online");
        return user;
    }
}
