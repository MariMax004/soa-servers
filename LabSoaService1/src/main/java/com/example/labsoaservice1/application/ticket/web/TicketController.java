package com.example.labsoaservice1.application.ticket.web;

import com.example.labsoaservice1.application.common.Endpoints;
import com.example.labsoaservice1.application.domain.Page;
import com.example.labsoaservice1.application.ticket.model.*;
import com.example.labsoaservice1.application.ticket.service.TicketService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping(Endpoints.TICKET)
    public void createTicket(@RequestBody TicketDto ticketDto) {
        ticketService.addTicket(ticketDto);
    }

    @GetMapping(Endpoints.TICKETS)
    public List<TicketDto> getTicketsDto() {
        return ticketService.getAllTickets();
    }

    @GetMapping(Endpoints.TICKET_ID)
    public TicketDto getTicketDtoById(@PathVariable Long ticketId) {
        return ticketService.getTicket(ticketId);
    }

    @DeleteMapping(Endpoints.TICKET_ID)
    public void deleteTicketDtoById(@PathVariable Long ticketId) {
        ticketService.deleteTicket(ticketId);
    }

    @PutMapping(Endpoints.TICKET)
    public void updateTicketDtoById(@RequestBody TicketDto ticketDto) {
        ticketService.updateTicket(ticketDto);
    }

    @GetMapping(Endpoints.TICKET_MAX_NAME)
    public List<TicketDto> getMaxNameTicketDto() {
        return ticketService.getMaxNameTicketDto();
    }


    @GetMapping(Endpoints.TICKETS_START_FROM_NAME)
    public List<TicketDto> getTicketsDtoFromName(@RequestParam String name) {
        return ticketService.getTicketsDtoFromName(name);
    }

    @PostMapping(Endpoints.TICKETS_FILTER)
    public Page<TicketDto> searchTicket(@RequestBody TicketFilter filterParams) {
        return ticketService.searchTicket(filterParams);
    }

    @GetMapping(Endpoints.SELL_TICKET_WITH_PRICE)
    public TicketDto sellTicketWithPrice(@PathVariable Long ticketId, @PathVariable Long personId, @RequestParam String price) {
        return ticketService.sellTicketWithPrice(ticketId, personId, Double.parseDouble(price));
    }

    @DeleteMapping(Endpoints.EVENT_DELETE)
    public void cancelEvent(@PathVariable Long eventId) {
        ticketService.cancelEvent(eventId);
    }

    @GetMapping("/coordinates")
    public List<CoordinatesDto> getCoordinatesDto() {
        return ticketService.getCoordinates();
    }

    @GetMapping("/venue")
    public List<VenueDto> getAllVenueDto() {
        return ticketService.getAllVenueDto();
    }
    @GetMapping("/venue/{venueId}")
    public VenueDto getAllVenueDto(@PathVariable Long venueId) {
        return ticketService.getVenueDtoById(venueId);
    }
    @GetMapping("/event")
    public List<EventDto> getAllEventDto() {
        return ticketService.getAllEventDto();
    }
    @GetMapping("/event/{eventId}")
    public EventDto getEventDtoById(@PathVariable Long eventId) {
        return ticketService.getEventDtoById(eventId);
    }
    @GetMapping("/person")
    public List<PersonDto> getAllPerson() {
        return ticketService.getAllPearsonDto();
    }
    @GetMapping("/person/{personId}")
    public PersonDto getPersonDtoById(@PathVariable Long personId) {
        return ticketService.getPersonById(personId);
    }
}
