package com.example.chatsdk.repositories;
import com.example.chatsdk.models.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    public List<Message> findByChatId(String chatId);

    public List<Message> findUnreadMessagesByReceiverId(String receiverId);
}
