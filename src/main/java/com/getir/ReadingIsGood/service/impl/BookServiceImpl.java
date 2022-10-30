package com.getir.ReadingIsGood.service.impl;

import com.getir.ReadingIsGood.dto.request.BookRequestDto;
import com.getir.ReadingIsGood.dto.response.BookResponseDto;
import com.getir.ReadingIsGood.entity.BookEntity;
import com.getir.ReadingIsGood.exception.ErrorType;
import com.getir.ReadingIsGood.exception.GlobalException;
import com.getir.ReadingIsGood.repository.BookRepository;
import com.getir.ReadingIsGood.service.BookService;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private static final Logger logger =
            (Logger) LoggerFactory.getLogger(BookServiceImpl.class);

    @Transactional
    @Override
    public BookResponseDto addBook(BookRequestDto bookRequestDto){
        logger.debug("***************************************");
        logger.debug("BookRequestDto BOOKID for add book" + bookRequestDto.getBookID());
        BookEntity bookEntity = modelMapper.map(bookRequestDto, BookEntity.class);
        BookEntity savedBook ;

        if (checkBookExists(bookEntity)) {
            logger.debug("Book is exist");
            bookEntity.setQuantity(getBookQuantity(bookEntity) + bookEntity.getQuantity());
            updateQuantity(bookEntity);
            savedBook = bookEntity;
        } else {
            logger.debug("Book is not exist");
            bookEntity.setCreatedDate(LocalDateTime.now());
            savedBook = bookRepository.save(bookEntity);
            savedBook.setQuantity(bookEntity.getQuantity());
        }
        logger.debug("Saved Book:" + savedBook.getBookID());
        return BookResponseDto.builder()
                .title(savedBook.getTitle())
                .author(savedBook.getCategory())
                .price(savedBook.getPrice())
                .category(savedBook.getCategory())
                .build();
    }

    @Transactional
    @Override
    public BookResponseDto updateBookStock(BookRequestDto bookRequestDto) {
        logger.debug("***************************************");
        logger.debug("BookRequestDto BOOKID for update book stock" + bookRequestDto.getBookID());
        BookEntity bookEntity = modelMapper.map(bookRequestDto, BookEntity.class);

        if (!checkBookExists(bookEntity)) {
            logger.debug("Book is not exist");
            throw new GlobalException(ErrorType.BOOK_IS_NOT_EXIST);
        }
        bookEntity.setQuantity(getBookQuantity(bookEntity) + bookEntity.getQuantity());
        updateQuantity(bookEntity);
        logger.debug("Book quantity: " + bookEntity.getQuantity());
        return BookResponseDto.builder()
                .title(bookEntity.getTitle())
                .author(bookEntity.getCategory())
                .price(bookEntity.getPrice())
                .category(bookEntity.getCategory())
                .build();

    }

    private boolean checkBookExists(BookEntity bookEntity){
        return bookRepository.existsByTitle(bookEntity.getTitle());
    }

    public long getBookQuantity(BookEntity bookEntity){
        BookEntity book =  bookRepository.getByTitle(bookEntity.getTitle());
        return book.getQuantity();
    }

    public int updateQuantity(BookEntity bookEntity){
        return bookRepository.updateQuantity(bookEntity.getTitle(), bookEntity.getQuantity());
    }
}

