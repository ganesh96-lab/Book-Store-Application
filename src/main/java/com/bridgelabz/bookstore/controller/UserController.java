/*
package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.ResponserDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.exception.UserException;
import com.bridgelabz.bookstore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/book-store/user")
public class UserController {

    @Autowired
    UserDetails userService;

    @PostMapping("/register")
    public ResponseEntity<ResponserDto> registration(@Valid @RequestBody UserDto userDto) throws UserException {
        User user = userService.registerUser(userDto);
        ResponserDto responserDto = new ResponserDto("user registeration done", user);
        return new ResponseEntity<ResponserDto>(responserDto, HttpStatus.OK);
    }

*/
/*    @PostMapping("/login")
    public ResponseEntity userLogin(@RequestBody LoginDto loginDto){
      String islogin =  userService.userLogin(loginDto);
        return new ResponseEntity(islogin, HttpStatus.OK);
    }*//*

}
*/
