package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.modelmapper.EntityToDtoMapper;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityToDtoMapper entityToDtoMapper;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Page<BookDto> searchBook(String searchBookString, Pageable pageable) {
       List<Book> relatedBookList=new ArrayList<>();
       List<Book> bookList = bookRepository.findAll();
       for(Book book:bookList){
           if(book.getTitle().toLowerCase().contains(searchBookString.toLowerCase()) ||
                   book.getAuthor().toLowerCase().contains(searchBookString.toLowerCase())){
               relatedBookList.add(book);
           }
       }
       return entityToDtoMapper.entityToDto(relatedBookList, pageable);
    }

    @Override
    public Page<BookDto> showAllBooks(Pageable pageable) {
        List<Book> bookList=new ArrayList<>();
        List<Book> allBooks = bookRepository.findAll();
        for (Book book : allBooks){
            bookList.add(book);
        }
        return entityToDtoMapper.entityToDto(bookList, pageable);
    }

    @Override
    public Page<BookDto> sortBooksByPriceAsc(Pageable pageable) {
        List<Book> allByOrderByPriceAsc = bookRepository.findAllByOrderByPriceAsc();
        return entityToDtoMapper.entityToDto(allByOrderByPriceAsc, pageable);
    }

    @Override
    public Page<BookDto> sortBooksByPriceDesc(Pageable pageable) {
        List<Book> allByOrderByPriceDesc = bookRepository.findAllByOrderByPriceDesc();
        return entityToDtoMapper.entityToDto(allByOrderByPriceDesc, pageable);
    }

    @Override
    public Page<BookDto> sortBooksByPublishDate(Pageable pageable) {
        List<Book> allByOrderByPublishDateDesc = bookRepository.findAllByOrderByPublishDateDesc();
        System.out.println(allByOrderByPublishDateDesc);
        return entityToDtoMapper.entityToDto(allByOrderByPublishDateDesc, pageable);
    }

    @Override
    public String verifyUserAccount(long userId) {
        Optional<User> user = userRepository.findById(userId);
        user.get().setVerified(true);
        userRepository.save(user.get());
        return "Congratulation account is verified";
    }

}
