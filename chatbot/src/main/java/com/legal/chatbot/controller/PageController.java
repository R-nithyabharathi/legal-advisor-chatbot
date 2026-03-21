package com.legal.chatbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {

    @GetMapping("/lawyer")  // URL you use in JS
    public String showLawyersPage() {
        return "lawyer";      // this must match lawyers.html
    }

    @GetMapping("/categories")
    public String categoriesPage() {
        return "categories";   // categories.html
    }

    @GetMapping("/user/dashboard")
    public String userDashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("username", userDetails.getUsername());
        return "user-dashboard";  // Must match the template name
    }

    @GetMapping("/chat")
    public String chatPage() {
        return "chat";
    }
}

