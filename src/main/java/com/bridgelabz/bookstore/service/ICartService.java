package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CartDto;
import com.bridgelabz.bookstore.model.Book;

import java.util.List;

public interface ICartService {
    String addToCart(CartDto cartDto);

    String removeFromCart(CartDto cartDto);

    List<Book> getAllBooksFromCart(int userId);
}