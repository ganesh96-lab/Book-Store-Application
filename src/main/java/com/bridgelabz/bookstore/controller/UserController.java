package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.ResponserDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.exception.UserException;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/book-store")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponserDto> registration(@Valid @RequestBody UserDto userDto) throws UserException {
        User user = userService.registerUser(userDto);
        ResponserDto responserDto = new ResponserDto("user registeration done", user);
        return new ResponseEntity<ResponserDto>(responserDto, HttpStatus.OK);
    }
}
