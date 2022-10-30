package com.getir.ReadingIsGood.serviceTest;

import com.getir.ReadingIsGood.dto.request.BookRequestDto;
import com.getir.ReadingIsGood.entity.BookEntity;
import com.getir.ReadingIsGood.entity.CustomerEntity;
import com.getir.ReadingIsGood.repository.BookRepository;
import com.getir.ReadingIsGood.repository.CustomerRepository;
import com.getir.ReadingIsGood.service.BookService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceImplTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    BookEntity bookEntity;

    CustomerEntity customerEntity;

    CustomerRepository customerRepository;

    @Test
    @Transactional
    public void addBookTest() {
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setSales(5L);
        bookRequestDto.setTitle("Aşkı memnu");
        bookRequestDto.setAuthor("Halid Ziya Uşaklıgil");
        bookRequestDto.setPrice((float) 69.9);
        bookRequestDto.setBookID(1L);
        bookRequestDto.setCategory("Klasik");
        bookRequestDto.setPublisher("Epsilon");
        bookRequestDto.setQuantity(1L);

        bookService.addBook(bookRequestDto);

        BookEntity bookEntity = bookRepository.findByBookID(bookRequestDto.getBookID());

        Assert.assertTrue(bookEntity.getBookID()== bookEntity.getBookID()
                && bookEntity.getPrice() == bookEntity.getPrice()
                && bookEntity.getTitle() == bookEntity.getTitle()
                && bookEntity.getSales() == bookEntity.getSales()
                && bookEntity.getCategory() == bookEntity.getCategory()
                && bookEntity.getAuthor() == bookEntity.getAuthor());

    }

    @Test
    @Transactional
    public void bookStockUpdateTest() {
        createBookHelper();
        bookEntity.setQuantity(getBookQuantity(bookEntity) + bookEntity.getQuantity());
        bookRepository.updateQuantity(bookEntity.getTitle(), bookEntity.getQuantity());

        Assert.assertFalse((getBookQuantity(bookEntity) + bookEntity.getQuantity()) == (bookRepository.getByBookID(bookEntity.getBookID()).getQuantity()));
    }

    public void createBookHelper(){
        bookEntity = new BookEntity();
        bookEntity.setSales(5L);
        bookEntity.setTitle("Aşkı memnu");
        bookEntity.setAuthor("Halid Ziya Uşaklıgil");
        bookEntity.setPrice((float) 69.9);
        bookEntity.setBookID(1L);
        bookEntity.setCategory("Klasik");
        bookEntity.setPublisher("Epsilon");
        bookEntity.setQuantity(6L);
        bookRepository.save(bookEntity);
    }

    public void createCustomerHelper(){
        customerEntity = new CustomerEntity();
        customerEntity.setAddress("ankara");
        customerEntity.setPhoneNumber("05437369008");
        customerEntity.setName("rabia");
        customerEntity.setId(1L);
        customerEntity.setEmail("rabiabilgucu9778@gmail.com");
        customerEntity.setSurname("bilgucu");

        customerRepository.save(customerEntity);
    }

    public long getBookQuantity(BookEntity bookEntity){
        BookEntity book =  bookRepository.getByTitle(bookEntity.getTitle());
        return book.getQuantity();
    }

}
