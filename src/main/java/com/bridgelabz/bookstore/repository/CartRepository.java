package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    boolean existsCartByUserId(long userId);
    boolean existsCartByBookId(String bookId);

    List<Cart> findAllByUserId(long userId);

    Optional<Cart> findByUserIdAndBookId(long userId, String bookId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Cart cart WHERE cart.bookId = :bookId AND cart.userId = :userId")
    void deleteCartsByBookIdAndUserId(@Param("bookId") String bookId, @Param("userId") long userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Cart cart WHERE cart.userId = :userId")
    void deleteAllByUserId(@Param("userId") long userId);
}
