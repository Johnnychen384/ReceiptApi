package com.example.receipt_api.mapper;

import com.example.receipt_api.domain.Item;
import com.example.receipt_api.domain.Receipt;
import com.example.receipt_api.json.ItemRequestJson;
import com.example.receipt_api.json.ReceiptRequestJson;
import org.springframework.stereotype.Component;
import java.util.UUID;


@Component
public class ReceiptMapper implements ToDomainMapper<ReceiptRequestJson, Receipt> {
    private final ToDomainMapper<ItemRequestJson, Item> mapper;

    public ReceiptMapper(ToDomainMapper<ItemRequestJson, Item> mapper) {
        this.mapper = mapper;
    }

    @Override
    public Receipt toDomain(ReceiptRequestJson json) {
        return Receipt.builder()
                .id(UUID.randomUUID().toString())
                .retailer(json.getRetailer())
                .purchaseDate(json.getPurchaseDate())
                .purchaseTime(json.getPurchaseTime())
                .items(json.getItems()
                        .stream()
                        .map(mapper::toDomain)
                        .toList())
                .total(json.getTotal())
                .build();
    }
}
