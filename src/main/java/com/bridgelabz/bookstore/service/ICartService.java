package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CartDto;

public interface ICartService {
    String addToCart(CartDto cartDto);

    String removeFromCart(CartDto cartDto);
}