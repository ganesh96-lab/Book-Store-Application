package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
@RequestMapping("/book-store")
public class CustomerBookStoreController
 {
    @Autowired
    private IBookService bookService;

    @GetMapping("/searchBook")
    public ResponseEntity<Page<BookDto>> searchBook(@RequestParam String searchBookString, @PageableDefault(size=10) Pageable pageable){
        return new ResponseEntity<>(bookService.searchBook(searchBookString, pageable), HttpStatus.OK);
    }

    @GetMapping("/showAllBooks")
    public ResponseEntity<Page<BookDto>> showallBooks(@PageableDefault(size = 10) Pageable pageable){
        return new ResponseEntity<>(bookService.showAllBooks(pageable), HttpStatus.OK);
    }

    @GetMapping("/sortAscByPrice")
    public ResponseEntity<Page<BookDto>> sortBooksByPriceAsc(@PageableDefault(size = 10) Pageable pageable){
        return new ResponseEntity<>(bookService.sortBooksByPriceAsc(pageable), HttpStatus.OK);
    }

    @GetMapping("/sortDescByPrice")
    public ResponseEntity<Page<BookDto>> sortByPriceDesc(@PageableDefault(size = 10) Pageable pageable){
        return new ResponseEntity<>(bookService.sortBooksByPriceDesc(pageable), HttpStatus.OK);
    }

    @GetMapping("/sortBynewArrival")
    public ResponseEntity<Page<BookDto>> sortByPublishDate(@PageableDefault(size = 10) Pageable pageable){
        return new ResponseEntity<>(bookService.sortBooksByPublishDate(pageable), HttpStatus.OK);
    }
}
