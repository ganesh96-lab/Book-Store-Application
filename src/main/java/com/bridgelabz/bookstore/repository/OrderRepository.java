package com.bridgelabz.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.bookstore.model.OrderBookDetails;

public interface OrderRepository extends JpaRepository<OrderBookDetails, Long> {
	OrderBookDetails findByUserId(Long id);

}
