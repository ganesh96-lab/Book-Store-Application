package com.bridgelabz.bookstore.service;


import org.springframework.http.ResponseEntity;

import com.bridgelabz.bookstore.payload.request.LoginRequest;
import com.bridgelabz.bookstore.payload.request.SignupRequest;

public interface IAuthenticateUserService {

    ResponseEntity authenticateUser(LoginRequest loginRequest);
    ResponseEntity registerUser(SignupRequest signUpRequest);
}