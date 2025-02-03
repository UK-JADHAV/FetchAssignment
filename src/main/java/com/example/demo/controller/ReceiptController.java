package com.example.demo.controller;


import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Receipt;
import com.example.demo.service.ReceiptService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/process")
    public Map<String, String> processReceipt(@RequestBody Receipt receipt) {
        String id = receiptService.processReceipt(receipt);
        Map<String, String> response = new HashMap<>();
        response.put("id", id);
        return response;
    }

    @GetMapping("/{id}/points")
    public Map<String, Integer> getPoints(@PathVariable String id) {
        Map<String, Integer> response = new HashMap<>();
        response.put("points", receiptService.getPoints(id));
        return response;
    }
}