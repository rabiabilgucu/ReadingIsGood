package com.getir.ReadingIsGood.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponseDto {

    private String name;
    private String surname;

    public String toString(){
        return "Dear customer " + name + " " + surname + " your registration has been done.";
    }

}
