package com.example.receipt_api.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Receipt {
    private String id;
    private String retailer;
    private String purchaseDate;
    private String purchaseTime;
    private List<Item> items;
    private String total;
}
