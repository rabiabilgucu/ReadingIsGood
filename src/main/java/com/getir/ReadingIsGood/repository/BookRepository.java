package com.getir.ReadingIsGood.repository;

import com.getir.ReadingIsGood.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    boolean existsByTitle(String title);

    BookEntity getByTitle(String title);

    BookEntity getByBookID(long title);

    BookEntity findByBookID(long id);

    @Modifying
    @Query("UPDATE BookEntity b SET b.quantity = :quantity WHERE b.title = :title")
    int updateQuantity(@Param("title") String title, @Param("quantity") Long quantity);

}
