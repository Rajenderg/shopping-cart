package com.shoppingcart.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingcart.dto.CartItemRequest;
import com.shoppingcart.dto.CheckoutResponse;
import com.shoppingcart.exception.CartItemNotFoundException;
import com.shoppingcart.model.CartItem;
import com.shoppingcart.repository.CartItemRepository;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;

    public CartService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartItem> getAllItems() {
        return cartItemRepository.findAll();
    }

    public CartItem getItem(Long id) {
        return cartItemRepository.findById(id)
                .orElseThrow(() -> new CartItemNotFoundException(id));
    }

    @Transactional
    public CartItem addItem(CartItemRequest request) {
        CartItem cartItem = new CartItem(request.getProductName(), request.getPrice(), request.getQuantity());
        return cartItemRepository.save(cartItem);
    }

    @Transactional
    public CartItem updateItem(Long id, CartItemRequest request) {
        CartItem existing = getItem(id);
        existing.setProductName(request.getProductName());
        existing.setPrice(request.getPrice());
        existing.setQuantity(request.getQuantity());
        return cartItemRepository.save(existing);
    }

    @Transactional
    public void deleteItem(Long id) {
        System.out.println("testing1");
        CartItem existing = getItem(id);
        cartItemRepository.delete(existing);
    }

    @Transactional
    public CheckoutResponse checkout() {
        List<CartItem> items = cartItemRepository.findAll();
        double totalAmount = items.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        cartItemRepository.deleteAll();
        return new CheckoutResponse(items, totalAmount, "Payment successful. Cart has been cleared.");
    }
}
