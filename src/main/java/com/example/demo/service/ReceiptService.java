package com.example.demo.service;


import org.springframework.stereotype.Service;

import com.example.demo.model.Receipt;
import com.example.demo.model.ReceiptItem;

import java.util.*;
import java.util.UUID;

@Service
public class ReceiptService {
    private final Map<String, Integer> receiptPoints = new HashMap<>();

    public String processReceipt(Receipt receipt) {
        String id = UUID.randomUUID().toString();
        int points = calculatePoints(receipt);
        receiptPoints.put(id, points);
        return id;
    }

    public Integer getPoints(String id) {
        return receiptPoints.getOrDefault(id, 0);
    }

    private int calculatePoints(Receipt receipt) {
        int points = 0;

        points += receipt.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length();
  
        double totalAmount = Double.parseDouble(receipt.getTotal());
        if (totalAmount == Math.floor(totalAmount)) {
            points += 50;
        }
   
        if (totalAmount % 0.25 == 0) {
            points += 25;
        }
     
        points += (receipt.getItems().size() / 2) * 5;
 
        for (ReceiptItem item : receipt.getItems()) {
            if (item.getShortDescription().length() % 3 == 0) {
                points += Math.ceil(Double.parseDouble(item.getPrice()) * 0.2);
            }
        }

        int day = Integer.parseInt(receipt.getPurchaseDate().split("-")[2]);
        if (day % 2 == 1) {
            points += 6;
        }
     
        int hour = Integer.parseInt(receipt.getPurchaseTime().split(":")[0]);
        int minute = Integer.parseInt(receipt.getPurchaseTime().split(":")[1]);
        if (hour == 14 || (hour == 15 && minute < 60)) {
            points += 10;
        }

        return points;
    }
}