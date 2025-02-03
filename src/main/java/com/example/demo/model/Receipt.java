package com.example.demo.model;

import java.util.List;

public class Receipt {
    private String retailer;
    private String purchaseDate;
    private String purchaseTime;
    private List<ReceiptItem> items;
    private String total;

    public String getRetailer() 
    { return retailer; }
    public String getPurchaseDate() 
    { return purchaseDate; }
    public String getPurchaseTime() 
    { return purchaseTime; }
    public List<ReceiptItem> getItems() 
    { return items; }
    public String getTotal() 
    { return total; }
}

