package com.bridgelabz.bookstore.dto;

import com.bridgelabz.bookstore.model.User;
public class ResponserDto {
    String message;
    User user;

    public ResponserDto(String message, User user){
        this.message = message;
        this.user = user;
    }

    @Override
    public String toString() {
        return "ResponserDto{" +
                "message='" + message + '\'' +
                ", user=" + user +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
