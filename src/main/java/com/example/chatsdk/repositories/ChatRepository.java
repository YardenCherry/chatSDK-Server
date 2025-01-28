package com.example.chatsdk.repositories;
import com.example.chatsdk.models.Chat;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ChatRepository extends MongoRepository<Chat, String> {


    // Find chats where the user is either user1 or user2
    List<Chat> findByUser1IdOrUser2Id(String user1Id, String user2Id);

    // Find a chat between two specific users
    Chat findByUser1IdAndUser2Id(String user1Id, String user2Id);

}


