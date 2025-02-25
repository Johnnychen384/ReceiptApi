package com.example.receipt_api.mapper;

import com.example.receipt_api.domain.Item;
import com.example.receipt_api.json.ItemRequestJson;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper implements ToDomainMapper<ItemRequestJson, Item>{
    @Override
    public Item toDomain(ItemRequestJson json) {
        return Item.builder()
                .shortDescription(json.getShortDescription())
                .price(json.getPrice())
                .build();
    }
}
