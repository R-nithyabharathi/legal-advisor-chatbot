package com.legal.chatbot.repository;

import com.legal.chatbot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long> {
    // Must return Optional<User> for orElseThrow to work
    Optional<User> findByUsername(String username);
}
