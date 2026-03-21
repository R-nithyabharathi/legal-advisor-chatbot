package com.legal.chatbot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "legal_faq")
public class LegalFaq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="category_id")
    private Long categoryId;

    private String topic;

    private String question;

    @Column(length = 5000)
    private String answer;

    public Long getId() { return id; }

    public Long getCategoryId() { return categoryId; }

    public String getTopic() { return topic; }

    public String getQuestion() { return question; }

    public String getAnswer() { return answer; }

    public void setId(Long id) { this.id = id; }

    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public void setTopic(String topic) { this.topic = topic; }

    public void setQuestion(String question) { this.question = question; }

    public void setAnswer(String answer) { this.answer = answer; }
}