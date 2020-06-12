package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CartDto;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.Cart;
import com.bridgelabz.bookstore.modelmapper.EntityToDtoMapper;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.CartRepository;
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

    @Override
    public String addToCart(CartDto cartDto) {
        Cart cart = entityToDtoMapper.convertToCartEntity(cartDto);
        if (cartRepository.existsCartByUserId(cart.getUserId()) && cartRepository.existsCartByBookId(cart.getBookId()))
            cartRepository.deleteCartsByBookIdAndUserId(cart.getBookId(), cart.getUserId());
        cartRepository.save(cart);
        return "Added to cart";
    }

    @Override
    public String removeFromCart(CartDto cartDto) {
        Cart cart = entityToDtoMapper.convertToCartEntity(cartDto);
        cartRepository.deleteCartsByBookIdAndUserId(cart.getBookId(), cart.getUserId());
        return "Book Removed Successfully";
    }

    @Override
    public List<Book> getAllBooksFromCart(int userId) {
        List<Book> cartBooks=new ArrayList<>();
        List<Cart> allByUserId = cartRepository.findAllByUserId(userId);
        for(Cart cart  : allByUserId){
            cartBooks.add(bookRepository.findById(cart.getBookId()));
        }
        return cartBooks;
    }
}
