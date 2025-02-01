package com.example.chatsdk.services;

import com.example.chatsdk.models.Chat;

import com.example.chatsdk.models.User;

import com.example.chatsdk.repositories.ChatRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.List;

import java.util.Optional;

@Service

public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    public List<Chat> getChatsByUserId(String userId) {

        return chatRepository.findByUser1IdOrUser2Id(userId,userId);

    }


    public Chat getChatBetweenUsers(String user1Id, String user2Id) {

        return chatRepository.findByUser1IdAndUser2Id(user1Id, user2Id);

    }

    public Chat createOrGetChat(User user1, User user2) {

        Chat existingChat1 = chatRepository.findByUser1IdAndUser2Id(user1.getId(), user2.getId());
        if (existingChat1 != null) {
            return existingChat1;
        }

        Chat existingChat2 = chatRepository.findByUser1IdAndUser2Id(user2.getId(), user1.getId());
        if (existingChat2 != null) {
            return existingChat2;
        }

        Chat newChat = new Chat();
        newChat.setUsername1(user1.getUsername());
        newChat.setUsername2(user2.getUsername());
        newChat.setUser1Id(user1.getId());
        newChat.setUser2Id(user2.getId());
        newChat.setLastMessage("");
        newChat.setLastMessageTime(LocalDateTime.now());

        return chatRepository.save(newChat);

    }

    public Chat updateLastMessage(String chatId, String lastMessage, LocalDateTime messageTime) {

        Optional<Chat> optionalChat = chatRepository.findById(chatId);

        if (optionalChat.isPresent()) {

            Chat chat = optionalChat.get();

            chat.setLastMessage(lastMessage);

            chat.setLastMessageTime(messageTime);

            return chatRepository.save(chat);

        }

        return null;

    }


    public Chat getChatById(String chatId) {

        return chatRepository.findById(chatId).orElse(null);

    }

    public void deleteAllChats() {
        chatRepository.deleteAll();
    }
}


