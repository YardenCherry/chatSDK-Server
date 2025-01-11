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

    /**
     * Send a message in a chat.
     *
     * @param chatId   The ID of the chat.
     * @param senderId The ID of the sender.
     * @param receiverId The ID of the receiver.
     * @param content  The message content.
     * @return The created Message object.
     */
    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(
            @RequestParam Long chatId,
            @RequestParam Long senderId,
            @RequestParam Long receiverId,
            @RequestParam String content) {

        Chat chat = chatService.getChatById(chatId);
        if (chat == null) {
            return ResponseEntity.badRequest().body("Invalid chat ID");
        }

        User sender = userService.findById(senderId);
        User receiver = userService.findById(receiverId);

        if (sender == null || receiver == null) {
            return ResponseEntity.badRequest().body("Invalid sender or receiver ID");
        }

        Message message = messageService.sendMessage(chat, sender, receiver, content);
        chatService.updateLastMessage(chatId, content, LocalDateTime.now()); // Update last message in chat
        return ResponseEntity.ok(message);
    }

    /**
     * Get all messages in a specific chat.
     *
     * @param chatId The ID of the chat.
     * @return A list of messages in the chat.
     */
    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<Message>> getMessagesByChatId(@PathVariable Long chatId) {
        List<Message> messages = messageService.getMessagesByChatId(chatId);
        return ResponseEntity.ok(messages);
    }

    /**
     * Mark a message as read.
     *
     * @param messageId The ID of the message.
     * @return The updated Message object.
     */
    @PostMapping("/{messageId}/read")
    public ResponseEntity<?> markMessageAsRead(@PathVariable Long messageId) {
        Message message = messageService.markMessageAsRead(messageId);
        if (message == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(message);
    }

    /**
     * Get unread messages for a user.
     *
     * @param userId The ID of the user.
     * @return A list of unread messages for the user.
     */
    @GetMapping("/unread/{userId}")
    public ResponseEntity<List<Message>> getUnreadMessages(@PathVariable Long userId) {
        List<Message> unreadMessages = messageService.getUnreadMessagesByUserId(userId);
        return ResponseEntity.ok(unreadMessages);
    }
}