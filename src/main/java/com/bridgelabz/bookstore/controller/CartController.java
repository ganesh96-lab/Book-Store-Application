package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.CartDto;
import com.bridgelabz.bookstore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("home/user/cart")
public class CartController {

    @Autowired
    private ICartService iCartService;

    @PutMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody CartDto cartDto) {
        return new ResponseEntity<String>(iCartService.addToCart(cartDto), HttpStatus.OK);
    }

    @PutMapping("/remove")
    public ResponseEntity<String> removeFromCart(@RequestBody CartDto cartDto) {
        return new ResponseEntity<String>(iCartService.removeFromCart(cartDto), HttpStatus.OK);
    }
    @GetMapping("/getall/{userId}")
    public ResponseEntity<String> getAllBooksFromCart(@PathVariable int userId){
        return new ResponseEntity<String>(iCartService.getAllBooksFromCart(userId), HttpStatus.OK);
    }
}