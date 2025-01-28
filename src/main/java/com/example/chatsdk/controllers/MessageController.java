package com.example.chatsdk.controllers;

import com.example.chatsdk.models.Message;
import com.example.chatsdk.models.Chat;
import com.example.chatsdk.models.User;
import com.example.chatsdk.services.MessageService;
import com.example.chatsdk.services.ChatService;
import com.example.chatsdk.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(
            @RequestParam String chatId,
            @RequestParam String senderId,
            @RequestParam String receiverId,
            @RequestParam String content) {

        Chat chat = chatService.getChatById(chatId);
        if (chat == null) {
            return ResponseEntity.badRequest().body("Invalid chat ID");
        }

        User sender = userService.loadUser(senderId);
        User receiver = userService.loadUser(receiverId);

        if (sender == null || receiver == null) {
            return ResponseEntity.badRequest().body("Invalid sender or receiver ID");
        }

        Message message = messageService.sendMessage(chat, sender, receiver, content);
        chatService.updateLastMessage(chatId, content, LocalDateTime.now()); // Update last message in chat
        return ResponseEntity.ok(message);
    }

    @GetMapping("/messages-in-chat/{chatId}")
    public ResponseEntity<List<Message>> getMessagesByChatId(@PathVariable String chatId) {
        List<Message> messages = messageService.getMessagesByChatId(chatId);
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/{messageId}/mark-as-read")
    public ResponseEntity<?> markMessageAsRead(@PathVariable String messageId) {
        Message message = messageService.markMessageAsRead(messageId);
        if (message == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(message);
    }


    @GetMapping("/unread/{userId}")
    public ResponseEntity<List<Message>> getUnreadMessages(@PathVariable String userId) {
        List<Message> unreadMessages = messageService.getUnreadMessagesByUserId(userId);
        return ResponseEntity.ok(unreadMessages);
    }
}