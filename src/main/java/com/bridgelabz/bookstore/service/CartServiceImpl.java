package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CartDto;
import com.bridgelabz.bookstore.dto.CartResponseDto;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.Cart;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.security.jwt.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    @Transactional
    public String addToCart(CartDto cartDto, String token) {
        long userId = jwtUtils.getUserIdFromJwtToken(token);
        String bookId = cartDto.getBookId();

        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (!bookOptional.isPresent()) {
            throw new BookStoreException(MessageReference.BOOK_NOT_FOUND);
        }

        if (cartDto.getBookQuantity() < 1) {
            throw new BookStoreException(MessageReference.INVALID_QUANTITY);
        }

        Optional<Cart> existingCart = cartRepository.findByUserIdAndBookId(userId, bookId);
        if (existingCart.isPresent()) {
            Cart cart = existingCart.get();
            cart.setBookQuantity(cart.getBookQuantity() + cartDto.getBookQuantity());
            cartRepository.save(cart);
        } else {
            Cart cart = new Cart();
            cart.setUserId(userId);
            cart.setBookId(bookId);
            cart.setBookQuantity(cartDto.getBookQuantity());
            cartRepository.save(cart);
        }

        return MessageReference.ADDED_TO_CART;
    }

    @Override
    @Transactional
    public String removeFromCart(CartDto cartDto, String token) {
        long userId = jwtUtils.getUserIdFromJwtToken(token);
        String bookId = cartDto.getBookId();

        Optional<Cart> existingCart = cartRepository.findByUserIdAndBookId(userId, bookId);
        if (!existingCart.isPresent()) {
            throw new BookStoreException(MessageReference.BOOK_NOT_IN_CART);
        }

        cartRepository.deleteCartsByBookIdAndUserId(bookId, userId);
        return MessageReference.Book_Removed;
    }

    @Override
    public List<CartResponseDto> getAllBooksFromCart(String token) {
        long userId = jwtUtils.getUserIdFromJwtToken(token);
        List<CartResponseDto> cartResponseList = new ArrayList<>();
        List<Cart> allByUserId = cartRepository.findAllByUserId(userId);

        for (Cart cart : allByUserId) {
            Optional<Book> bookOptional = bookRepository.findById(cart.getBookId());
            if (bookOptional.isPresent()) {
                Book book = bookOptional.get();
                CartResponseDto responseDto = new CartResponseDto();
                responseDto.setCartId(cart.getId());
                responseDto.setBookId(book.getId());
                responseDto.setBookTitle(book.getTitle());
                responseDto.setBookAuthor(book.getAuthor());
                responseDto.setBookImage(book.getImage());
                responseDto.setBookPrice(book.getPrice());
                responseDto.setBookQuantity(cart.getBookQuantity());
                responseDto.setTotalPrice(book.getPrice() * cart.getBookQuantity());
                cartResponseList.add(responseDto);
            }
        }

        return cartResponseList;
    }

    @Override
    @Transactional
    public String updateCartQuantity(CartDto cartDto, String token) {
        long userId = jwtUtils.getUserIdFromJwtToken(token);
        String bookId = cartDto.getBookId();

        Optional<Cart> existingCart = cartRepository.findByUserIdAndBookId(userId, bookId);
        if (!existingCart.isPresent()) {
            throw new BookStoreException(MessageReference.BOOK_NOT_IN_CART);
        }

        if (cartDto.getBookQuantity() < 1) {
            throw new BookStoreException(MessageReference.INVALID_QUANTITY);
        }

        Cart cart = existingCart.get();
        cart.setBookQuantity(cartDto.getBookQuantity());
        cartRepository.save(cart);
        return MessageReference.CART_UPDATED;
    }

    @Override
    @Transactional
    public String clearCart(String token) {
        long userId = jwtUtils.getUserIdFromJwtToken(token);
        cartRepository.deleteAllByUserId(userId);
        return MessageReference.CART_CLEARED;
    }

    @Override
    public int getCartItemCount(String token) {
        long userId = jwtUtils.getUserIdFromJwtToken(token);
        List<Cart> allByUserId = cartRepository.findAllByUserId(userId);
        return allByUserId.size();
    }
}
