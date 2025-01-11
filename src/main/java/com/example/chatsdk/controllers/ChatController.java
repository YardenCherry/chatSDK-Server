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

    /**
     * Get all chats for a specific user.
     *
     * @param userId The ID of the user.
     * @return A list of chats for the user.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Chat>> getChatsForUser(@PathVariable Long userId) {
        List<Chat> chats = chatService.getChatsByUserId(userId);
        if (chats.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(chats);
    }

    /**
     * Create or get a chat between two users.
     *
     * @param user1Id The ID of the first user.
     * @param user2Id The ID of the second user.
     * @return The created or retrieved Chat object.
     */
    @PostMapping("/create")
    public ResponseEntity<?> createOrGetChat(@RequestParam Long user1Id, @RequestParam Long user2Id) {
        // Validate users
        User user1 = userService.findById(user1Id);
        User user2 = userService.findById(user2Id);

        if (user1 == null || user2 == null) {
            return ResponseEntity.badRequest().body("Invalid user IDs provided.");
        }

        // Create or retrieve chat
        Chat chat = chatService.createOrGetChat(user1, user2);
        return ResponseEntity.ok(chat);
    }

    /**
     * Get a specific chat by its ID.
     *
     * @param chatId The ID of the chat.
     * @return The Chat object if found.
     */
    @GetMapping("/{chatId}")
    public ResponseEntity<?> getChatById(@PathVariable Long chatId) {
        Chat chat = chatService.getChatById(chatId);
        if (chat == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(chat);
    }

    /**
     * Update the last message in a chat.
     *
     * @param chatId      The ID of the chat.
     * @param lastMessage The content of the last message.
     * @return The updated Chat object.
     */
    @PutMapping("/{chatId}/lastMessage")
    public ResponseEntity<?> updateLastMessage(@PathVariable Long chatId, @RequestParam String lastMessage) {
        Chat chat = chatService.updateLastMessage(chatId, lastMessage, LocalDateTime.now());
        if (chat == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(chat);
    }
}