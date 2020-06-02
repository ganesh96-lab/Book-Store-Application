package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBookService {
     Page<BookDto> searchBook(String searchBookString, Pageable pageable);
     Page<BookDto> showAllBooks(Pageable pageable);
     Page<BookDto> sortBooksByPriceAsc(Pageable pageable);
     Page<BookDto> sortBooksByPriceDesc(Pageable pageable);
     Page<BookDto> sortBooksByPublishDate(Pageable pageable);
}
