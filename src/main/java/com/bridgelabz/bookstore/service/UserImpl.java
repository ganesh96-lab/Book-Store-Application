package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.exception.UserException;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.repository.UserRepository;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserImpl implements IUserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(UserDto userDto) throws UserException {
        userDto.password = passwordEncoder.encode(userDto.password).trim();
        User user = new User(userDto);
        Optional<User> byEmailId = userRepository.findByEmail(userDto.email);
        if(byEmailId.isPresent()){
            throw new UserException("User Already present");
        }
        userRepository.save(user);
        return user;
    }
}
