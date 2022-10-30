package com.getir.ReadingIsGood.service;

import com.getir.ReadingIsGood.dto.request.BookRequestDto;
import com.getir.ReadingIsGood.dto.response.BookResponseDto;
import com.getir.ReadingIsGood.entity.BookEntity;

public interface BookService {
    BookResponseDto addBook(BookRequestDto bookRequestDto);

    BookResponseDto updateBookStock(BookRequestDto bookRequestDto);

    long getBookQuantity(BookEntity bookEntity);

    int updateQuantity(BookEntity bookEntity);
}
