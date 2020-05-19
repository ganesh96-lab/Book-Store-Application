package com.bridgelabz.bookstore.Service;

import com.bridgelabz.bookstore.Model.Book;
import com.bridgelabz.bookstore.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
@Service

public class BookServiceImpl {
    @Autowired
    private BookRepository bookRepository;
    public void saveBookData(){
        String line="";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/ganesh/Community version/Book-Store-App/src/main/resources/books_data.csv"));
            bufferedReader.readLine();
            while ((line=bufferedReader.readLine())!=null){
                System.out.println(line);
                String data[]=line.split(",");
                Book book=new Book();
                book.setId(data[0]);
                book.setAuthor(data[1]);
                book.setTitle(data[2]);
                book.setImage(data[3]);
                book.setPrice(data[4]);
                book.setDescription(data[5]);
                bookRepository.save(book);
            }
        }catch (IOException e){
            e.printStackTrace();

        }
    }
}
