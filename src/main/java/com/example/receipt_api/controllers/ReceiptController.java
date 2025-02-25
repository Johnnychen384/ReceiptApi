package com.example.receipt_api.controllers;

import com.example.receipt_api.domain.Receipt;
import com.example.receipt_api.json.PointsResponseJson;
import com.example.receipt_api.json.ReceiptRequestJson;
import com.example.receipt_api.json.ReceiptResponseJson;
import com.example.receipt_api.mapper.ToDomainMapper;
import com.example.receipt_api.service.ReceiptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    private final ReceiptService receiptService;
    private final ToDomainMapper<ReceiptRequestJson, Receipt> mapper;

    public ReceiptController(
            ReceiptService receiptService,
            ToDomainMapper<ReceiptRequestJson, Receipt> mapper
    ) {
        this.receiptService = receiptService;
        this.mapper = mapper;
    }

    @PostMapping("/process")
    public ResponseEntity<ReceiptResponseJson> processReceipt(@RequestBody ReceiptRequestJson req) {
        ReceiptResponseJson res = receiptService.processReceipt(mapper.toDomain(req));
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<PointsResponseJson> getPoints(@PathVariable String id) {
        return ResponseEntity.ok(receiptService.getPoints(id));
    }
}
