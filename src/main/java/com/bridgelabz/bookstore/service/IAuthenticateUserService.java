package com.bridgelabz.bookstore.service;


import org.springframework.http.ResponseEntity;

import com.bridgelabz.bookstore.dto.Setpassworddto;
import com.bridgelabz.bookstore.payload.request.LoginRequest;
import com.bridgelabz.bookstore.payload.request.SignupRequest;
import com.bridgelabz.bookstore.response.Response;

public interface IAuthenticateUserService {

    ResponseEntity authenticateUser(LoginRequest loginRequest);
    ResponseEntity registerUser(SignupRequest signUpRequest);
	Response findEmail(String email);
	Response setPassword(Setpassworddto setpassworddto, String token);
	Response valivateUser(String token);
	
}