package com.getir.ReadingIsGood.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookResponseDto {
    private String title;
    private String author;
    private Float price;
    private String category;

    private Long quantity;

    public String toString(){
        return "Title: " + title + " Author: " + author + " Price: " + price + " Category: " + category;
    }
}
