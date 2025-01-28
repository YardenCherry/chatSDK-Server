package com.example.chatsdk.controllers;

import com.example.chatsdk.models.Chat;
import com.example.chatsdk.models.User;
import com.example.chatsdk.services.ChatService;
import com.example.chatsdk.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;


    @GetMapping("/get-all-chats-of-user")
    public ResponseEntity<List<Chat>> getChatsForUser(@PathVariable String userId) {
        List<Chat> chats = chatService.getChatsByUserId(userId);
        if (chats.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(chats);
    }


    @PostMapping("/create")
    public ResponseEntity<?> createOrGetChat(@RequestParam String user1Id, @RequestParam String user2Id) {
        // Validate users
        User user1 = userService.loadUser(user1Id);

        User user2 = userService.loadUser(user2Id);

        if (user1 == null || user2 == null) {
            return ResponseEntity.badRequest().body("Invalid user IDs provided.");
        }

        Chat chat = chatService.createOrGetChat(user1, user2);
        return ResponseEntity.ok(chat);
    }


    @GetMapping("/{chatId}")
    public ResponseEntity<?> getChatById(@PathVariable String chatId) {
        Chat chat = chatService.getChatById(chatId);
        if (chat == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(chat);
    }

    @PutMapping("/{chatId}/update-last-message")
    public ResponseEntity<?> updateLastMessage(@PathVariable String chatId, @RequestParam String lastMessage) {
        Chat chat = chatService.updateLastMessage(chatId, lastMessage, LocalDateTime.now());
        if (chat == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(chat);
    }
}