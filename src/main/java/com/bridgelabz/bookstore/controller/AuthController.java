package com.bridgelabz.bookstore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.Setpassworddto;
import com.bridgelabz.bookstore.payload.request.LoginRequest;
import com.bridgelabz.bookstore.payload.request.SignupRequest;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.service.IAuthenticateUserService;
import com.bridgelabz.bookstore.service.IBookService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private IAuthenticateUserService authenticateUserService;
    
    @Autowired
    private IBookService bookService;
    

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    	
    	return authenticateUserService.authenticateUser(loginRequest);
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    	
    	return authenticateUserService.registerUser(signUpRequest);
    }
    
    @GetMapping("/verifyuser/{userId}")
    public ResponseEntity<String> verifyAccount(@PathVariable long userId){
        return new ResponseEntity<>(bookService.verifyUserAccount(userId), HttpStatus.OK);
    }
    
    @PostMapping("/forgetpassword")
    public ResponseEntity<Response> findEmail(@RequestHeader String email){
    	return new ResponseEntity<Response>(authenticateUserService.findEmail(email), HttpStatus.OK);
    }
    
    @PostMapping("/setnewpassword")
    public ResponseEntity<Response> setNewPassword(@RequestHeader String token, @RequestBody Setpassworddto setpassworddto){
    	return new ResponseEntity<Response>(authenticateUserService.setPassword(setpassworddto, token), HttpStatus.OK);
    }
    
	@PostMapping("/validate")
	public ResponseEntity<Response> validate(@RequestParam String token) {

		return new ResponseEntity<Response>(authenticateUserService.valivateUser(token), HttpStatus.OK);
	}
}