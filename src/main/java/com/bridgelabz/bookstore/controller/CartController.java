package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.CartDto;
import com.bridgelabz.bookstore.dto.CartResponseDto;
import com.bridgelabz.bookstore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/home/user/cart")
public class CartController {

    @Autowired
    private ICartService iCartService;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@Valid @RequestBody CartDto cartDto, @RequestHeader String token) {
        return new ResponseEntity<>(iCartService.addToCart(cartDto, token), HttpStatus.OK);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFromCart(@RequestBody CartDto cartDto, @RequestHeader String token) {
        return new ResponseEntity<>(iCartService.removeFromCart(cartDto, token), HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<CartResponseDto>> getAllBooksFromCart(@RequestHeader String token) {
        return new ResponseEntity<>(iCartService.getAllBooksFromCart(token), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCartQuantity(@Valid @RequestBody CartDto cartDto, @RequestHeader String token) {
        return new ResponseEntity<>(iCartService.updateCartQuantity(cartDto, token), HttpStatus.OK);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart(@RequestHeader String token) {
        return new ResponseEntity<>(iCartService.clearCart(token), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getCartItemCount(@RequestHeader String token) {
        return new ResponseEntity<>(iCartService.getCartItemCount(token), HttpStatus.OK);
    }
}
