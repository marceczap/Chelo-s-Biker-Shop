package com.example.miappbiker;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private final List<CartItem> items;

    private CartManager() {
        items = new ArrayList<>();
    }

    public static synchronized CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void addItem(String id, String name, int imageRes, String priceString, int unitPrice, String color, String category, int quantity) {
        for (CartItem item : items) {
            if (item.getName().equalsIgnoreCase(name) && item.getColor().equalsIgnoreCase(color)) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(id, name, imageRes, priceString, unitPrice, color, category, quantity));
    }

    public void removeItem(int position) {
        if (position >= 0 && position < items.size()) {
            items.remove(position);
        }
    }

    public void updateQuantity(int position, int delta) {
        if (position >= 0 && position < items.size()) {
            CartItem item = items.get(position);
            int newQty = item.getQuantity() + delta;
            if (newQty <= 0) {
                items.remove(position);
            } else if (newQty <= 10) {
                item.setQuantity(newQty);
            }
        }
    }

    public int getTotalCount() {
        int count = 0;
        for (CartItem item : items) {
            count += item.getQuantity();
        }
        return count;
    }

    public int getTotalUSD() {
        int total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public int getTotalBs() {
        return (int) Math.round(getTotalUSD() * 6.96);
    }

    public void clearCart() {
        items.clear();
    }
}
