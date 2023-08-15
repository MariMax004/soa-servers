package com.example.labsoaservice2.application.ticket.model;

import lombok.Data;

@Data
public class Filter {

    private String name;

    private FilterType type;

    private String value;
}
