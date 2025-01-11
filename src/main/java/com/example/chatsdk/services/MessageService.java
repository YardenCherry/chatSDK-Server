package com.example.chatsdk.services;

import com.example.chatsdk.models.Chat;
import com.example.chatsdk.models.Message;
import com.example.chatsdk.models.User;
import com.example.chatsdk.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    /**
     * Send a message from a sender to a receiver in a specific chat.
     *
     * @param chat     The chat to which the message belongs.
     * @param sender   The user who sends the message.
     * @param receiver The user who receives the message.
     * @param content  The content of the message.
     * @return The saved Message object.
     */
    public Message sendMessage(Chat chat, User sender, User receiver, String content) {
        Message message = new Message(chat, sender, receiver, content);
        return messageRepository.save(message);
    }

    /**
     * Retrieve all messages for a specific chat.
     *
     * @param chatId The ID of the chat.
     * @return A list of messages in the specified chat.
     */
    public List<Message> getMessagesByChatId(Long chatId) {
        return messageRepository.findByChatId(chatId);
    }

    /**
     * Mark a message as read.
     *
     * @param messageId The ID of the message to be marked as read.
     * @return The updated Message object.
     */
    public Message markMessageAsRead(Long messageId) {
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if (optionalMessage.isPresent()) {
            Message message = optionalMessage.get();
            message.setRead(true);
            return messageRepository.save(message);
        }
        return null; // Handle case where message does not exist
    }

    /**
     * Get all unread messages for a specific user.
     *
     * @param userId The ID of the user.
     * @return A list of unread messages.
     */
    public List<Message> getUnreadMessagesByUserId(Long userId) {
        return messageRepository.findUnreadMessagesByReceiverId(userId);
    }
}
