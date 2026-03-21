package com.legal.chatbot.service;

import com.legal.chatbot.model.LegalFaq;
import com.legal.chatbot.repository.LegalFaqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatService {

    @Autowired
    private LegalFaqRepository repo;

    @Autowired
    private OllamaService ollamaService;

    private static final Set<String> STOP_WORDS = new HashSet<>(Arrays.asList(
            "what","is","the","to","how","a","an","of","for","in","about"
    ));

    public String getAnswer(String question){

        try {
            question = question.toLowerCase().trim();

            System.out.println("User Question: " + question);

            // 1️⃣ Exact match
            LegalFaq faq = repo.findByQuestionIgnoreCase(question);

            if (faq != null && faq.getAnswer() != null) {
                return faq.getAnswer();
            }

            // 2️⃣ Keyword search
            for(String word : question.split(" ")){

                if(STOP_WORDS.contains(word)) continue;

                List<LegalFaq> results = repo.searchLaw(word);

                if(results != null && !results.isEmpty() && results.get(0).getAnswer() != null){
                    return results.get(0).getAnswer();
                }
            }

            // 3️⃣ Ask Ollama safely
            try {
                String aiResponse = ollamaService.askAI(question);

                if (aiResponse != null && !aiResponse.isEmpty()) {
                    return aiResponse;
                }

            } catch (Exception e) {
                System.out.println("Ollama Error: " + e.getMessage());
            }

            // 4️⃣ Final fallback
            return "Sorry, I couldn't find an answer. Please consult a legal expert.";

        } catch (Exception e) {
            e.printStackTrace();
            return "Something went wrong. Please try again later.";
        }
    }
}