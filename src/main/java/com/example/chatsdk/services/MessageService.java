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

    public Message sendMessage(Chat chat, User sender, User receiver, String content) {
        Message message = new Message(chat, sender, receiver, content);
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByChatId(String chatId) {
        return messageRepository.findByChatId(chatId);
    }

    public Message markMessageAsRead(String messageId) {
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if (optionalMessage.isPresent()) {
            Message message = optionalMessage.get();
            message.setRead(true);
            return messageRepository.save(message);
        }
        return null; // Handle case where message does not exist
    }

    public List<Message> getUnreadMessagesByUserId(String userId) {
        return messageRepository.findUnreadMessagesByReceiverId(userId);
    }
}
