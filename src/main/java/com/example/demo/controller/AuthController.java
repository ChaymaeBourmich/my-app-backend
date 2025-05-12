package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "https://my-app-frontend-beta.vercel.app")

@RestController
@RequestMapping("/api/public")
public class AuthController {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User existingUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful!");
            response.put("roles", existingUser.getRoles());
            response.put("premiereConnexion", existingUser.isPremiereConnexion()); // ✅ ici
            return ResponseEntity.ok(response);
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

}