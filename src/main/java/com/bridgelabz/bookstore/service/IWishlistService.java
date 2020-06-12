package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.WishlistDto;

public interface IWishlistService {
    String addToWishlist(WishlistDto wishlistDto);
    String removeFromWishlist(WishlistDto wishlistDto);
}
