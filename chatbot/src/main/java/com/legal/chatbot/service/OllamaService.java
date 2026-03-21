package com.legal.chatbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class OllamaService {

    @Autowired
    private RestTemplate restTemplate;

    private final String OLLAMA_API = "http://localhost:11434/api/generate";

    public String askAI(String question) {

        try {
            Map<String, Object> request = new HashMap<>();
            request.put("model", "tinyllama"); // ✅ good choice
            request.put("prompt", "You are a helpful Indian legal advisor. Give a short and simple answer: " + question);
            request.put("stream", false);

            Map response = restTemplate.postForObject(OLLAMA_API, request, Map.class);

            if(response != null && response.get("response") != null){
                return response.get("response").toString();
            }

            return "Sorry, I could not generate an answer.";

        } catch (Exception e) {
            System.out.println("Ollama Error: " + e.getMessage());

            // ✅ fallback response
            return "AI service is currently unavailable. Please try again later.";
        }
    }
}