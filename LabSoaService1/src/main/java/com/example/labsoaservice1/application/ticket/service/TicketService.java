package com.example.labsoaservice1.application.ticket.service;

import com.example.labsoaservice1.application.domain.Page;
import com.example.labsoaservice1.application.ticket.domain.Person;
import com.example.labsoaservice1.application.ticket.model.*;

import java.util.List;

public interface TicketService {
    TicketDto getTicket(long id);
    List<TicketDto> getAllTickets();
    void addTicket(TicketDto ticketDto);
    void updateTicket(TicketDto ticketDto);
    void deleteTicket(Long id);
    List<TicketDto> getMaxNameTicketDto();
    List<TicketDto> getTicketsDtoFromName(String name);
    void cancelEvent(Long id);
    TicketDto sellTicketWithPrice(Long ticketId, Long personId, Double price);
    Page<TicketDto> searchTicket (TicketFilter ticketFilter);
    List<CoordinatesDto> getCoordinates();
    List<VenueDto> getAllVenueDto();
    List<EventDto> getAllEventDto();
    List<PersonDto> getAllPearsonDto();
    PersonDto getPersonById(Long personId);
    EventDto getEventDtoById(Long eventId);
    VenueDto getVenueDtoById(Long venueId);
}
