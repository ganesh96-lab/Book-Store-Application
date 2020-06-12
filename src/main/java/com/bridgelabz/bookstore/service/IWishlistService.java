package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.WishlistDto;
import com.bridgelabz.bookstore.model.Book;

import java.util.List;

public interface IWishlistService {
    String addToWishlist(WishlistDto wishlistDto);
    String removeFromWishlist(WishlistDto wishlistDto);

    List<Book> getAllBooks(int userId);
}
