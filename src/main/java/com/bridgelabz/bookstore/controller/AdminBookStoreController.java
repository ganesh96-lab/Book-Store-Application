package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.service.IAdminService;
import com.bridgelabz.bookstore.service.IBookService;
import com.mysql.fabric.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/book-store/admin")
public class AdminBookStoreController {

    @Autowired
    private IAdminService adminService;

    @PostMapping("/addbook")
    public String addSingleBook(@RequestBody Book book){
        adminService.addSingleBook(book);
        return "Single book record inserted";
    }

/*    @DeleteMapping("/deleteRecord/{id}")
    public String deleteBook(@PathVariable int id){
        adminService.deleteBookById(id);
        return "Deleted Successfully";
    }*/


    @PostMapping("/uploadfile")
    public String uploadFile(@RequestParam("selectFile") MultipartFile multipartFile ) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        adminService.saveBookData(bufferedReader);
        return "File uploaded and saved in db";
    }
}
