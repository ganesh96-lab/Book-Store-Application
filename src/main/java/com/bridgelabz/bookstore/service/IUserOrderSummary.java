package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.model.Cart;

public interface IUserOrderSummary {
	public String placeOrder(String token);
	public int calculateTotalPrice(List<Cart> listCart);

}
