package com.example.labsoaservice2.application.ticket.model;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TicketFilter extends PageableRequest {

    private Sort sort;

    private List<Filter> filters;
}

