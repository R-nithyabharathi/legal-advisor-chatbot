package com.legal.chatbot.controller;
import com.legal.chatbot.dto.ChatRequestDTO;
import com.legal.chatbot.dto.ChatResponseDTO;
import com.legal.chatbot.model.LegalFaq;
import com.legal.chatbot.repository.LegalFaqRepository;
import com.legal.chatbot.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/ask")
    public String ask(@RequestBody String question){


        return chatService.getAnswer(question);
    }
}



