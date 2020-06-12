package com.bridgelabz.bookstore.dto;

import java.io.Serializable;

public class WishlistDto implements Serializable {

    private int userId;
    private int bookId;

    public WishlistDto(int userId, int bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}