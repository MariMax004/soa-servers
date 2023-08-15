package com.example.labsoaservice1.application.ticket.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TicketFilter extends PageableRequest {

    private Sort sort;

    private List<Filter> filters;
}

