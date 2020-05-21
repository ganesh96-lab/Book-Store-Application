package com.bridgelabz.bookstore.Controller;

import com.bridgelabz.bookstore.Model.Book;
import com.bridgelabz.bookstore.Service.BookServiceImpl;
import com.bridgelabz.bookstore.Service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book-store")
public class BookStoreController {
    @Autowired
    private IBookService bookService;

    @GetMapping("/feedBookdata")
    public void setDataInDB(){
        bookService.saveBookData();
    }

    @GetMapping("/searchBook")
    public ResponseEntity<List<Book>> searchBook(@RequestParam String searchBookString){
        return new ResponseEntity<>(bookService.searchBook(searchBookString), HttpStatus.OK);
    }

    @GetMapping("/showAllBooks")
    public ResponseEntity<List<Book>> showallBooks(){
        return new ResponseEntity<>(bookService.showAllBooks(), HttpStatus.OK);
    }
}
