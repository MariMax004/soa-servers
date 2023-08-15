package com.example.labsoaservice2.application.ticket.model;

import lombok.Data;

@Data
public class PageableRequest {


    private Integer limit = 20;

    private Integer page = 1;

}
