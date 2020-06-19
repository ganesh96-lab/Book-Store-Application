package com.bridgelabz.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bridgelabz.bookstore.dto.PasswordForgotDto;

@Controller
@RequestMapping("/forgot-password")
public class PasswordForgotController {

    @ModelAttribute("forgotPasswordDto")
    public PasswordForgotDto forgotPasswordDto() {
        return new PasswordForgotDto();
    }
    
	/*
	 * @PostMapping public String processForgetPassword() {
	 * 
	 * }
	 */
}
