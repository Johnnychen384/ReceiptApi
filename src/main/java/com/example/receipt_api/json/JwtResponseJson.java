package com.example.receipt_api.json;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponseJson {
    private String token;
}
