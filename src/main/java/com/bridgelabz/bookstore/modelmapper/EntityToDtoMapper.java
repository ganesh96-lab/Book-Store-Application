package com.bridgelabz.bookstore.modelmapper;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.dto.CartDto;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.Cart;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityToDtoMapper{
    ModelMapper modelMapper=new ModelMapper();

    public Page<BookDto> entityToDto(List<Book> book, Pageable pageable){
        List<BookDto> bookDtoList = Arrays.asList(modelMapper.map(book, BookDto[].class));
        return new PageImpl<>(bookDtoList,pageable,bookDtoList.size());
    }

    public BookDto convertToBookDto(Book book) {
        return modelMapper.map(book, BookDto.class);
    }

    public Book convertToBookEntity(BookDto bookDto) {
        return modelMapper.map(bookDto, Book.class);
    }

    public CartDto convertToCartDto(Cart cart) {
        return modelMapper.map(cart, CartDto.class);
    }

    public Cart convertToCartEntity(CartDto cartDto) {
        return modelMapper.map(cartDto, Cart.class);
    }
}
