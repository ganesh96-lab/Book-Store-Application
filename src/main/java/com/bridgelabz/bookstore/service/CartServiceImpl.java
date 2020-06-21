package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CartDto;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.Cart;
import com.bridgelabz.bookstore.modelmapper.EntityToDtoMapper;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.security.jwt.JwtUtils;
import com.bridgelabz.bookstore.utility.Tokenutility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private EntityToDtoMapper entityToDtoMapper;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private Tokenutility tokenutility;
    
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public String addToCart(CartDto cartDto, String token) {
    	
    	System.out.println("0");
    	System.out.println(token);
    	long userId = (int)(jwtUtils.getUserIdFromJwtToken(token));
    	System.out.println("1");
        Cart cart = entityToDtoMapper.convertToCartEntity(cartDto);
        cart.setUserId(userId);
		/*
		 * if (cartRepository.existsCartByUserId(userId) &&
		 * cartRepository.existsCartByBookId(bookId))
		 * cartRepository.deleteCartsByBookIdAndUserId(cart.getBookId(),
		 * cart.getUserId());
		 */
        System.out.println("2");
        cartRepository.save(cart);
        return "Added to cart";
    }

    @Override
    public String removeFromCart(CartDto cartDto, String token) {
    	
    	long userId = (jwtUtils.getUserIdFromJwtToken(token));
        Cart cart = entityToDtoMapper.convertToCartEntity(cartDto);
        cartRepository.deleteCartsByBookIdAndUserId(cart.getBookId(), userId);
        return "Book Removed Successfully";
    }

    @Override
    public List<Book> getAllBooksFromCart(String token) {
    	long userId = (int)(jwtUtils.getUserIdFromJwtToken(token));
        List<Book> cartBooks=new ArrayList<>();
	        List<Cart> allByUserId = cartRepository.findAllByUserId(userId);
	        System.out.println(allByUserId);
	        for(Cart cart  : allByUserId){
	            if(cart.getBookQuantity() == 0)
	                cartRepository.deleteCartsByBookIdAndUserId(cart.getBookId(), cart.getUserId());
	            cartBooks.add(bookRepository.findById(cart.getBookId()).get());
	        }
      
        return cartBooks;
    }
}
