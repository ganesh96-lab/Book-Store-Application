package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.model.Book;

import java.util.List;

public interface IBookService {
     void saveBookData();
     List<Book> searchBook(String searchBookString);
     List<Book> showAllBooks();
     List<Book> sortBooksByPriceAsc();
     List<Book> sortBooksByPriceDesc();
}
