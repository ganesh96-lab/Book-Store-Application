package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sun.jvm.hotspot.types.JLongField;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findAllByOrderByPriceAsc();
    List<Book> findAllByOrderByPriceDesc();
    List<Book> findAllByOrderByPublishDateDesc();
    Book findById(int bookId);
}
