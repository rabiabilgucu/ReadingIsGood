package com.getir.ReadingIsGood.controller;


import com.getir.ReadingIsGood.dto.request.BookRequestDto;
import com.getir.ReadingIsGood.dto.response.BookResponseDto;
import com.getir.ReadingIsGood.service.BookService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping("/addBook")
    public ResponseEntity<String> addBook(@Valid @RequestBody BookRequestDto bookRequestDto){
        BookResponseDto bookResponse = bookService.addBook(bookRequestDto);
        return new ResponseEntity(bookResponse, HttpStatus.OK);
    }

    @PostMapping("/updateBookStock")
    public ResponseEntity<String> updateBookStock(@Valid @RequestBody BookRequestDto bookRequestDto){
        BookResponseDto bookResponse = bookService.updateBookStock(bookRequestDto);
        return new ResponseEntity(bookResponse, HttpStatus.OK);
    }

}