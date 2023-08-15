package com.example.labsoaservice1.application.ticket.model;


import lombok.Data;


@Data
public class AddressDto {

    private String street;

    private String zipCode;

    private LocationDto town;
}
