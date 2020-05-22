package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBookService {
     void saveBookData();
     Page<BookDto> searchBook(String searchBookString, Pageable pageable);
     List<Book> showAllBooks();
     List<Book> sortBooksByPriceAsc();
     List<Book> sortBooksByPriceDesc();
     List<Book> sortBooksByPublishDate();
}
