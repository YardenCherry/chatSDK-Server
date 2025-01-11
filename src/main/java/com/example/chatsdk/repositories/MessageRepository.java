package com.example.chatsdk.repositories;
import com.example.chatsdk.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    // Find all messages in a specific chat
    public List<Message> findByChatId(Long chatId);

    // Find all unread messages for a specific receiver
    public List<Message> findUnreadMessagesByReceiverId(Long receiverId);
}
