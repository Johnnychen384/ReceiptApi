package com.example.receipt_api.service;

import com.example.receipt_api.domain.Receipt;
import com.example.receipt_api.json.PointsResponseJson;
import com.example.receipt_api.json.ReceiptResponseJson;

public interface ReceiptService {
    ReceiptResponseJson processReceipt(Receipt receipt);

    PointsResponseJson getPoints(String id);
}
