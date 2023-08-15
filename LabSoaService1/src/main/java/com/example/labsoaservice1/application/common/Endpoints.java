package com.example.labsoaservice1.application.common;

public interface Endpoints {
    String TICKETS = "/tickets";
    String TICKET_ID = "/ticket/{ticketId}";
    String TICKET = "/ticket";
    String TICKET_MAX_NAME = "/ticket/name/max";
    String TICKET_ID_MAX = "/ticket/{id}/max";
    String TICKETS_START_FROM_NAME = "/tickets/name";
    String TICKETS_FILTER = "/tickets/filter";
    String TICKETS_SORT = "/tickets/sort";
    String EVENT_DELETE = "/event/{eventId}/cancel";
    String SELL_TICKET_WITH_PRICE = "/sell/{ticketId}/{personId}/price";


}
