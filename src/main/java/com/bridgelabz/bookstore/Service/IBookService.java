package com.bridgelabz.bookstore.Service;

import com.bridgelabz.bookstore.Model.Book;

import java.util.List;

public interface IBookService {
     void saveBookData();
     public List<Book> searchBook(String searchBookString);
     public List<Book> showAllBooks();
}
