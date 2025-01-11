package com.example.chatsdk.repositories;
import com.example.chatsdk.models.Chat;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ChatRepository extends MongoRepository<Chat, Long> {

    // Find all chats for a specific user (user1 or user2)

    List<Chat> findByUser1IdOrUser2Id(Long user1Id, Long user2Id);

    // Find a chat between two specific users

    Chat findByUser1IdAndUser2Id(Long user1Id, Long user2Id);

}


