package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.service.IBookService;
import com.bridgelabz.bookstore.service.IRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.server.UnicastServerRef;

@RestController
@RequestMapping("/book-store")
public class UserController {

    @Autowired
    IRegistrationService registrationService;
    @PostMapping("/registration")
    public String registration(@RequestBody User user){
        registrationService.registerUser(user);
        return "registration done";
    }
}
