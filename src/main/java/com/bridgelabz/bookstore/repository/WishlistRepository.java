package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    boolean existsWishlistByUserId(int userId);
    boolean existsWishlistByBookId(String bookId);
    List<Wishlist> findAllByUserId(int userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Wishlist wishlist WHERE wishlist.bookId = :bookId AND wishlist.userId = :userId")
    void deleteWishlistByBookIdAndUserId(@Param("bookId") String bookId, @Param("userId") int userId);
}