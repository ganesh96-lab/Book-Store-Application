package com.bridgelabz.bookstore.modelmapper;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.model.Book;
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
}
