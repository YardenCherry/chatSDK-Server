package com.example.chatsdk.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String username;
    private String passwordHash;
    private String avatarUrl;
    private String status = "offline";

    private LocalDateTime createdAt = LocalDateTime.now();

    public User() {
    }

    public User(String username, String passwordHash, String avatarUrl) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.avatarUrl = avatarUrl;
        this.status = "offline";
        this.createdAt = LocalDateTime.now();
    }


    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
