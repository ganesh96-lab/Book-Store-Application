package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
        System.out.println("Getting data from db:"+userList);
        return userList;
    }
}
