package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("/book-store")
public class BookStoreController {
    @Autowired
    private IBookService bookService;

    @GetMapping("/feedBookdata")
    public String setDataInDB(){
        bookService.saveBookData();
        return "record Inserted";
    }

    @GetMapping("/searchBook")
    public ResponseEntity<Page<BookDto>> searchBook(@RequestParam String searchBookString,@PageableDefault(size=10) Pageable pageable){
        return new ResponseEntity<>(bookService.searchBook(searchBookString,pageable), HttpStatus.OK);
    }

    @GetMapping("/showAllBooks")
    public ResponseEntity<List<Book>> showallBooks(){
        return new ResponseEntity<>(bookService.showAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/sortAscByPrice")
    public ResponseEntity<List<Book>> sortBooksByPriceAsc(){
        return new ResponseEntity<>(bookService.sortBooksByPriceAsc(), HttpStatus.OK);
    }

    @GetMapping("/sortDescByPrice")
    public ResponseEntity<List<Book>> sortByPriceDesc(){
        return new ResponseEntity<>(bookService.sortBooksByPriceDesc(), HttpStatus.OK);
    }

    @GetMapping("/sortBynewArrival")
    public ResponseEntity<List<Book>> sortByPublishDate(){
        return new ResponseEntity<>(bookService.sortBooksByPublishDate(), HttpStatus.OK);
    }
}
