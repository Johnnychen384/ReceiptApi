package com.example.receipt_api.service;

import java.security.Key;

public interface JwtService {
    String generateToken();

    boolean isTokenValid(String token);
}
