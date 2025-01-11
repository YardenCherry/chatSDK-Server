package com.example.chatsdk.repositories;

import com.example.chatsdk.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    public Optional<User> findByUsername(String username);

    List<User> findAllByStatus(String status);
}
