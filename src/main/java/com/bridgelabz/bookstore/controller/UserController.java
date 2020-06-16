package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.IconUIResource;
import java.util.List;

@RestController("/home/user/")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/remove/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "user deleted where id is : "+id;
    }
}
