package com.bridgelabz.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.Cart;
import com.bridgelabz.bookstore.model.OrderBookDetails;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.repository.OrderRepository;
import com.bridgelabz.bookstore.security.jwt.JwtUtils;

@Service
public class UserOrderSummaryImpl implements IUserOrderSummary{
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private BookRepository bookRepository;

	public String placeOrder(String token) {
		
		System.out.println("1");
    	long userId = (jwtUtils.getUserIdFromJwtTokenLong(token));
    	System.out.println(userId);
    	System.out.println("2");
    	List<Cart> listCart = cartRepository.findAllByUserId(userId);
    	System.out.println(listCart);
    	OrderBookDetails orderBooks= new OrderBookDetails();//orderRepository.findByUserId(userId);
    	System.out.println("3");
    	//if(orderBooks == null) {
    		System.out.println("4");
    		orderBooks.setBookList(new ArrayList());
    		orderBooks.getBookList().addAll(listCart);
    		orderBooks.setTotalPrice(calculateTotalPrice(listCart));
    		System.out.println(orderBooks);
    		orderRepository.save(orderBooks);   	
    	return "Order placed successfully";	
	}
	
	public int calculateTotalPrice(List<Cart> listCart) {
		int totalPrice = 0;
		for(Cart cart : listCart){Book book =	bookRepository.findById(cart.getBookId()).get();
		totalPrice +=cart.getBookQuantity()*book.getPrice(); 
		}
		return totalPrice;
	}
}
