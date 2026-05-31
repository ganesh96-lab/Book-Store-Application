package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CartDto;
import com.bridgelabz.bookstore.dto.CartResponseDto;

import java.util.List;

public interface ICartService {
    String addToCart(CartDto cartDto, String token);

    String removeFromCart(CartDto cartDto, String token);

    List<CartResponseDto> getAllBooksFromCart(String token);

    String updateCartQuantity(CartDto cartDto, String token);

    String clearCart(String token);

    int getCartItemCount(String token);
}
