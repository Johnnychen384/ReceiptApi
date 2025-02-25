package com.example.receipt_api.service;

import com.example.receipt_api.domain.Item;
import com.example.receipt_api.domain.Receipt;
import com.example.receipt_api.exception.CustomBadRequest;
import com.example.receipt_api.json.PointsResponseJson;
import com.example.receipt_api.json.ReceiptResponseJson;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Component
public class ReceiptServiceImpl implements ReceiptService {
    Map<String, Receipt> db = new HashMap<>();

    @Override
    public ReceiptResponseJson processReceipt(Receipt receipt) {
        String id = receipt.getId();

        if (db.containsKey(id)) {
            throw new CustomBadRequest("Oops, this id already exists! Please try again.");
        }
        db.put(id, receipt);

        return ReceiptResponseJson
                .builder()
                .id(id)
                .build();
    }

    @Override
    public PointsResponseJson getPoints(String id) {
        if (!db.containsKey(id)) {
            throw new NoSuchElementException("No receipt found for that ID.");
        }

        Receipt receipt = db.get(id);
        return PointsResponseJson
                .builder()
                .points(calculatePoints(receipt))
                .build();
    }


    private int calculatePoints(Receipt receipt) {
        int points = (int) receipt
                .getRetailer()
                .chars()
                .filter(Character::isLetterOrDigit)
                .count();

        String total = receipt.getTotal();
        points += total.endsWith("00") ? 50 : 0;

        BigDecimal quarterValue = new BigDecimal("0.25");
        points += (new BigDecimal(total)
                .remainder(quarterValue)
                .compareTo(BigDecimal.ZERO) == 0) ? 25 : 0;

        points += (receipt.getItems().size() / 2) * 5;

        for (Item item : receipt.getItems()) {
            String trimmedDescription = item.getShortDescription().trim();
            if (trimmedDescription.length() % 3 == 0) {
                BigDecimal price = new BigDecimal(item.getPrice());
                BigDecimal itemPoints = price.multiply(new BigDecimal("0.2"));
                points += itemPoints.setScale(0, RoundingMode.CEILING).intValue();
            }
        }

        LocalDate purchaseDate = LocalDate.parse(receipt.getPurchaseDate());
        points += purchaseDate.getDayOfMonth() % 2 == 1 ? 6 : 0;

        LocalTime purchaseTime = LocalTime.parse(receipt.getPurchaseTime());
        LocalTime startTime = LocalTime.of(14, 0);
        LocalTime endTime = LocalTime.of(16, 0);

        points += purchaseTime.isAfter(startTime) && purchaseTime.isBefore(endTime) ? 10 : 0;

        return points;
    }
}
