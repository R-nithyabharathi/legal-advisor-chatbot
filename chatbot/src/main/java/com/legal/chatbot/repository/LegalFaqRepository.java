package com.legal.chatbot.repository;

import com.legal.chatbot.model.LegalFaq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LegalFaqRepository extends JpaRepository<LegalFaq, Long> {
    LegalFaq findByQuestionIgnoreCase(String question);

    @Query("SELECT l FROM LegalFaq l WHERE LOWER(l.question) LIKE LOWER(CONCAT('%', :word, '%'))")
    List<LegalFaq> searchLaw(String word);

}