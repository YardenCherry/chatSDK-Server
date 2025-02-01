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

    public Message sendMessage(String chatId, String senderId, String receiverId, String content) {
        Message message = new Message(chatId, senderId, receiverId, content);
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByChatId(String chatId) {
        return messageRepository.findByChatId(chatId);
    }

    public void deleteAllMessages() {
        messageRepository.deleteAll();
    }
}
