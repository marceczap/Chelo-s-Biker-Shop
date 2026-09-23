package com.example.miappbiker;

import java.io.Serializable;

public class CartItem implements Serializable {
    private String id;
    private String name;
    private int imageRes;
    private String priceString;
    private int unitPrice;
    private String color;
    private String category;
    private int quantity;

    public CartItem(String id, String name, int imageRes, String priceString, int unitPrice, String color, String category, int quantity) {
        this.id = id;
        this.name = name;
        this.imageRes = imageRes;
        this.priceString = priceString;
        this.unitPrice = unitPrice;
        this.color = color;
        this.category = category;
        this.quantity = quantity;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getImageRes() { return imageRes; }
    public String getPriceString() { return priceString; }
    public int getUnitPrice() { return unitPrice; }
    public String getColor() { return color; }
    public String getCategory() { return category; }
    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
    public int getTotalPrice() { return unitPrice * quantity; }
}
