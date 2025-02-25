package com.example.receipt_api.mapper;

public interface ToDomainMapper<J, D> {
    D toDomain(J json);
}
