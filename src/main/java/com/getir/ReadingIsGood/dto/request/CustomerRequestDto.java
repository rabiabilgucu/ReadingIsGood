package com.getir.ReadingIsGood.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequestDto {
    private long id;

    @NotBlank(message = "Customer name can not be blank!")
    private String name;

    @NotBlank(message = "Customer surname can not be blank")
    private String surname;

    @NotBlank(message = "The Customer phone number can not be blank!")
    private String phoneNumber;

    @NotBlank(message = "The Customer email can not be empty")
    @Email(message = "Email Should be valid")
    private String email;

    @NotBlank(message = "The Customer address can not be empty")
    private String address;

}
