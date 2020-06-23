package com.bridgelabz.bookstore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.OrderBookDetailDTO;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.service.IUserOrderSummary;
import com.bridgelabz.bookstore.service.MessageReference;

@RestController
@RequestMapping("/bookstore")
public class CustomerOrderDetailController {
	
	@Autowired
	private IUserOrderSummary userOrderSummary;
	@GetMapping("/placeorder")
	public String PlaceOrder(@RequestHeader String token){
		userOrderSummary.placeOrder(token);
		return MessageReference.ORDER_PLACED;
	}

}
