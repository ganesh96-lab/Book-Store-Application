package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
}