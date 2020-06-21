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
    public ResponseEntity<String> addToCart(@RequestBody CartDto cartDto, @RequestHeader String token) {
    	System.out.println(token);
        return new ResponseEntity<String>(iCartService.addToCart(cartDto, token), HttpStatus.OK);
    }

    @PutMapping("/remove")
    public ResponseEntity<String> removeFromCart(@RequestBody CartDto cartDto, @RequestHeader String token) {
        return new ResponseEntity<String>(iCartService.removeFromCart(cartDto, token), HttpStatus.OK);
    }
    
    @GetMapping("/getall")
    public ResponseEntity getAllBooksFromCart(@RequestHeader String token){
        return new ResponseEntity(iCartService.getAllBooksFromCart(token), HttpStatus.OK);
    }
}