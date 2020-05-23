package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.service.IAdminService;
import com.bridgelabz.bookstore.service.IBookService;
import com.mysql.fabric.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book-store/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @PostMapping("/addbook")
    public String addSingleBook(@RequestBody Book book){
        adminService.addSingleBook(book);
        return "Single book record inserted";
    }
}
