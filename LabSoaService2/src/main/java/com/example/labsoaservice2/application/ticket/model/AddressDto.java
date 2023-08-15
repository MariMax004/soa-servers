package com.example.labsoaservice2.application.ticket.model;

import lombok.Data;


@Data
public class AddressDto {
//    @Schema(name = "Улица")
    private String street;
//    @Schema(name = "Индекс")
    private String zipCode;
//    @Schema(name = "Город")
    private LocationDto town;
}
