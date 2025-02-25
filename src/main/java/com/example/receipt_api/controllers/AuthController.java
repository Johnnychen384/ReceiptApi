package com.example.receipt_api.controllers;

import com.example.receipt_api.json.JwtResponseJson;
import com.example.receipt_api.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/token")
    public ResponseEntity<JwtResponseJson> getToken() {
        String token = jwtService.generateToken();
        return ResponseEntity.ok(JwtResponseJson.builder().token(token).build());
    }
}
