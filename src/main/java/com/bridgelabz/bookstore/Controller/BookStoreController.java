package com.bridgelabz.bookstore.Controller;

import com.bridgelabz.bookstore.Service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookStoreController {
    @Autowired
    private BookServiceImpl bookService;

    @GetMapping("/feedBookdata")
    public void setDataInDB(){
        bookService.saveBookData();
    }
}
