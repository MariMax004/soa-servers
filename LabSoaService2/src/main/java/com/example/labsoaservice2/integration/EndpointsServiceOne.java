package com.example.labsoaservice2.integration;

public interface EndpointsServiceOne {
    String TICKETS = "/tickets";
    String TICKET_ID = "/ticket/{ticketId}";
    String TICKET = "/ticket";
    String TICKET_MIN_NAME = "/ticket/name/min";
    String TICKET_ID_MAX = "/ticket/{id}/max";
    String TICKETS_START_FROM_NAME = "/tickets/name";
    String TICKETS_FILTER = "/tickets/filter";
    String TICKETS_SORT = "/tickets/sort";
}
