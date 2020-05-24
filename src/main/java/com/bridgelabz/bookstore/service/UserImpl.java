package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationImpl implements IRegistrationService{

    @Autowired
    UserRepository userRepository;
    @Override
    public void registerUser(User user) {
        userRepository.save(user);
    }
}
