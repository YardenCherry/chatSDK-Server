package com.example.chatsdk.repositories;
import com.example.chatsdk.models.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message, Long> {

    // Find all messages in a specific chat
    public List<Message> findByChatId(Long chatId);

    // Find all unread messages for a specific receiver
    public List<Message> findUnreadMessagesByReceiverId(Long receiverId);
}
