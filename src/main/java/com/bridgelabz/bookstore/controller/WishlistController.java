package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.WishlistDto;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.service.IWishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home/user/wishlist")
public class WishlistController {

    @Autowired
    private IWishlistService iWishlistService;

    @PutMapping("/add")
    public ResponseEntity<String> addToWishlist(@RequestBody WishlistDto wishlistDto) {
        return new ResponseEntity<String>(iWishlistService.addToWishlist(wishlistDto), HttpStatus.OK);
    }

    @PutMapping("/remove")
    public ResponseEntity<String> removeFromWishlist(@RequestBody WishlistDto wishlistDto) {
        return new ResponseEntity<String>(iWishlistService.removeFromWishlist(wishlistDto), HttpStatus.OK);
    }

    @GetMapping("/getall/{userId}")
    public ResponseEntity<List<Book>> getAllBooksList(@PathVariable int userId){
        return new ResponseEntity<List<Book>>(iWishlistService.getAllBooks(userId), HttpStatus.OK);
    }
}
