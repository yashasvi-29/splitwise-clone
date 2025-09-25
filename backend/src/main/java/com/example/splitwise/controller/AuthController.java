package com.example.splitwise.controller;

import com.example.splitwise.model.User;
import com.example.splitwise.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // Simple signup endpoint (password is stored raw in this scaffold - **DO NOT** store raw passwords in production)
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Map<String, String> req){
        String username = req.get("username");
        String email = req.get("email");
        String password = req.get("password");

        if(userRepository.existsByUsername(username)){
            return ResponseEntity.badRequest().body(Map.of("message","Username already taken"));
        }
        if(userRepository.existsByEmail(email)){
            return ResponseEntity.badRequest().body(Map.of("message","Email already in use"));
        }
        User user = new User(username, email, password);
        userRepository.save(user);
        return ResponseEntity.ok(Map.of("message","User registered successfully"));
    }

    // Simple login returns a mock token (for full JWT see JwtUtils skeleton in security package)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> req){
        String username = req.get("username");
        String password = req.get("password");
        Optional<User> u = userRepository.findByUsername(username);
        if(u.isEmpty() || !u.get().getPassword().equals(password)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message","Invalid credentials"));
        }
        // Mock token: replace with real JWT
        String token = "MOCK-TOKEN-for-"+username;
        return ResponseEntity.ok(Map.of("token", token));
    }
}
