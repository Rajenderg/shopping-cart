package com.shoppingcart.dto;

import java.util.List;

import com.shoppingcart.model.CartItem;

public class CheckoutResponse {

    private List<CartItem> purchasedItems;
    private double totalAmount;
    private String message;

    public CheckoutResponse(List<CartItem> purchasedItems, double totalAmount, String message) {
        this.purchasedItems = purchasedItems;
        this.totalAmount = totalAmount;
        this.message = message;
    }

    public List<CartItem> getPurchasedItems() {
        return purchasedItems;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getMessage() {
        return message;
    }
}
