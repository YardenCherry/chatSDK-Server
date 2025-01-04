package com.example.chatsdk.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat; // Reference to the Chat this message belongs to

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender; // Reference to the User who sent the message

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver; // Reference to the User who received the message

    @Column(nullable = false)
    private String content; // The actual message content

    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now(); // When the message was sent

    @Column(nullable = false)
    private boolean isRead = false; // Whether the message has been read by the receiver

    // Constructors
    public Message() {
    }

    public Message(Chat chat, User sender, User receiver, String content) {
        this.chat = chat;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
