package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void addSingleBook(Book book) {
        bookRepository.save(book);
    }

    public void saveBookData(BufferedReader bufferedReader){
        String line="";
        try {
            //BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/ganesh/Community version/Book-Store-App/src/main/resources/books_data.csv"));
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
}
