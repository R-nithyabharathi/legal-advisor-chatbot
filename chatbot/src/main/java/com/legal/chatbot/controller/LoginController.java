package com.legal.chatbot.controller;

import com.legal.chatbot.model.User;
import com.legal.chatbot.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @GetMapping("/register")
    public String registerForm() {

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password) {
        if(userRepository.findByUsername(username).isPresent()) {
            return "redirect:/register?error"; // Username exists
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // Must encode
        user.setEmail("noemail@example.com");
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return "redirect:/login";
    }
}