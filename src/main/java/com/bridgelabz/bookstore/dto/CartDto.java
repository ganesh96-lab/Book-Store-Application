package com.bridgelabz.bookstore.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CartDto {

    @NotNull(message = "Book ID is required")
    private String bookId;

    @Min(value = 1, message = "Book quantity must be at least 1")
    private int bookQuantity;

    public CartDto() {
    }

    public CartDto(String bookId, int bookQuantity) {
        this.bookId = bookId;
        this.bookQuantity = bookQuantity;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(int bookQuantity) {
        this.bookQuantity = bookQuantity;
    }
}
