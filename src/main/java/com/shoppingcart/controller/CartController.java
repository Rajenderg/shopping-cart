package com.shoppingcart.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.dto.CartItemRequest;
import com.shoppingcart.dto.CheckoutResponse;
import com.shoppingcart.model.CartItem;
import com.shoppingcart.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/items")
    public ResponseEntity<List<CartItem>> getAllItems() {
        return ResponseEntity.ok(cartService.getAllItems());
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<CartItem> getItem(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.getItem(id));
    }

    @PostMapping("/items")
    public ResponseEntity<CartItem> addItem(@Valid @RequestBody CartItemRequest request) {
        CartItem created = cartService.addItem(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<CartItem> updateItem(@PathVariable Long id, @Valid @RequestBody CartItemRequest request) {
        return ResponseEntity.ok(cartService.updateItem(id, request));
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        cartService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/checkout")
    public ResponseEntity<CheckoutResponse> checkout() {
        return ResponseEntity.ok(cartService.checkout());
    }
}
