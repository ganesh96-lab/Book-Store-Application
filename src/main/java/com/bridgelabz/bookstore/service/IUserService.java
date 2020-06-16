package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.model.User;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();

    void deleteUser(Long id);
}
