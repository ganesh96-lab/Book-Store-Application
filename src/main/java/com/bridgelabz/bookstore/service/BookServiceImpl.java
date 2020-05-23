package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.modelmapper.EntityToDtoMapper;
import com.bridgelabz.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    EntityToDtoMapper entityToDtoMapper;

    @Autowired
    private BookRepository bookRepository;

    public void saveBookData(){
        String line="";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/ganesh/Community version/Book-Store-App/src/main/resources/books_data.csv"));
            bufferedReader.readLine();
            while ((line=bufferedReader.readLine())!=null){
               // System.out.println(line);
                String data[]=line.split(",");
                Book book=new Book();
                book.setId(data[0]);
                book.setAuthor(data[1]);
                book.setTitle(data[2]);
                book.setImage(data[3]);
                System.out.println("Price"+data[4]);
                book.setPrice(Integer.parseInt(data[4]));
                System.out.println("description"+data[5]);
                book.setDescription(data[5]);
                book.setPublishDate(LocalDate.now());
                bookRepository.save(book);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

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
}
