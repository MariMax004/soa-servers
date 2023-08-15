package com.example.labsoaservice1.application.ticket.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TicketDto {

    private Long id;

    private String name;

    private EventDto event;

    private CoordinatesDto coordinates;

    private LocalDateTime creationDate;

    private Double price;

    private TicketTypeDto type;

    private VenueDto venue;

    private PersonDto person;
}

