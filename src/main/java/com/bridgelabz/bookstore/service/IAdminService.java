package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.model.Book;

import java.io.BufferedReader;

public interface IAdminService {
    void saveBookData(BufferedReader bufferedReader);
    void addSingleBook(Book book);
    String deleteBook(String id);
    String updateBook(Book book);
}
