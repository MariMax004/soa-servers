package com.example.labsoaservice1.application.ticket.model;


import lombok.Data;

@Data
public class VenueDto {
    private long id;

    private String name;

    private int capacity;
//    @Schema(name = "Адрес")
//    private AddressDto address;
}
