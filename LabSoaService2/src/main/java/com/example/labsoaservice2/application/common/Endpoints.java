package com.example.labsoaservice2.application.common;

public interface Endpoints {
    String TICKETS = "/tickets";
    String TICKET_ID = "/ticket/{ticketId}";
    String TICKET = "/ticket";
    String TICKET_MAX_NAME = "/ticket/name/max";
    String TICKET_ID_MAX = "/ticket/id/max";
    String TICKETS_START_FROM_NAME = "/tickets/name";
    String TICKET_CHANGE_TO_VIP = "/sell/vip/{ticketId}/{personId}";
    String EVENT_DELETE = "/event/{eventId}/cancel";
    String TICKETS_FILTER = "/tickets/filter";
    String SELL_TICKET_WITH_PRICE = "/sell/{ticketId}/{personId}/price";


}
