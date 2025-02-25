package com.example.receipt_api.mapper;

public interface ToJsonMapper<D, J> {
    J toJson(D domain);
}
