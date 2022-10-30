package com.getir.ReadingIsGood.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class BookRequestDto {

    private Long bookID;

    @NotBlank(message = "Title can not be blank")
    private String title;

    @NotBlank(message = "Author can not be blank")
    private String author;

    @NotBlank(message = "Publisher can not be blank")
    private String publisher;

    @NotBlank(message = "Category can not be blank")
    private String category;

    @NotBlank(message = "Quantity can not be blank")
    private Long quantity;

    @NotBlank(message = "Sales can not be blank")
    private Long sales;

    @NotBlank(message = "Price can not be blank")
    private Float price;

}
