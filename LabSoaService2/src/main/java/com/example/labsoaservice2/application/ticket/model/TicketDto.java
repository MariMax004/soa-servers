package com.example.labsoaservice2.application.ticket.model;

import lombok.Data;

@Data
public class TicketDto {

    private Long id;

    private String name;

    private CoordinatesDto coordinates;

    private java.util.Date creationDate;

    private EventDto event;

    private Double price;

    private TicketTypeDto type;

    private VenueDto venue;

    private PersonDto person;
}

