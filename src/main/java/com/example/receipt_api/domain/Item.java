package com.example.receipt_api.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {
    private String shortDescription;
    private String price;
}
