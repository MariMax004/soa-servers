package com.example.labsoaservice2.application.ticket.web;

import com.example.labsoaservice2.application.common.Endpoints;
import com.example.labsoaservice2.application.domain.Page;
import com.example.labsoaservice2.application.ticket.model.CoordinatesDto;
import com.example.labsoaservice2.application.ticket.model.EventDto;
import com.example.labsoaservice2.application.ticket.model.PersonDto;
import com.example.labsoaservice2.application.ticket.model.TicketDto;
import com.example.labsoaservice2.application.ticket.model.TicketFilter;
import com.example.labsoaservice2.application.ticket.model.VenueDto;
import com.example.labsoaservice2.integration.ServiceOne;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
//@RequestMapping("/booking")
@RequiredArgsConstructor
public class TicketController {

    private final ServiceOne serviceOne;

    @PostMapping(Endpoints.TICKET)
    public TicketDto createTicket(@RequestBody TicketDto ticketDto) {
        return serviceOne.createTicket(ticketDto);
    }

    @GetMapping(Endpoints.TICKETS)
    public List<TicketDto> getTicketsDto() {
        log.info("Tickets");
        return serviceOne.getTicketsDto();
    }

    @GetMapping(Endpoints.TICKET_ID)
    public TicketDto getTicketDtoById(@PathVariable Long ticketId) {
        return serviceOne.getTicketDtoById(ticketId);
    }

    @DeleteMapping(Endpoints.TICKET_ID)
    public void deleteTicketDtoById(@PathVariable Long ticketId) {
        serviceOne.deleteTicketDtoById(ticketId);
    }

    @PutMapping(Endpoints.TICKET)
    public TicketDto updateTicketDtoById(@RequestBody TicketDto ticketDto) {
        return serviceOne.updateTicketDtoById(ticketDto);
    }

    @GetMapping(Endpoints.TICKET_MAX_NAME)
    public List<TicketDto> getMaxNameTicketDto() {
        return serviceOne.getMaxNameTicketDto();
    }

    @GetMapping(Endpoints.TICKETS_START_FROM_NAME)
    public List<TicketDto> getTicketsDtoFromName(@RequestParam String name) {
        return serviceOne.getTicketsDtoFromName(name);
    }

    @PutMapping(Endpoints.TICKET_CHANGE_TO_VIP)
    public TicketDto changeTicketDtoToVip(@PathVariable Long ticketId, @PathVariable String personId) {
        return null;
    }

    @GetMapping(Endpoints.SELL_TICKET_WITH_PRICE)
    public PersonDto sellTicketWithPrice(@PathVariable Long ticketId, @PathVariable Long personId, @RequestParam String price) {
        return serviceOne.sellTicketWithPrice(ticketId, personId, price);
    }

    @DeleteMapping(Endpoints.EVENT_DELETE)
    public void cancelEvent(@PathVariable Long eventId) {
        serviceOne.cancelEvent(eventId);
    }

    @PostMapping(Endpoints.TICKETS_FILTER)
    public Page<TicketDto> searchVehicle(@RequestBody TicketFilter filterParams) {
        return serviceOne.searchVehicle(filterParams);
    }

    @GetMapping("/coordinates")
    public List<CoordinatesDto> getCoordinatesDto() {
        return serviceOne.getCoordinatesDto();
    }

    @GetMapping("/venue")
    public List<VenueDto> getAllVenueDto() {
        return serviceOne.getAllVenueDto();
    }

    @GetMapping("/venue/{venueId}")
    public List<VenueDto> getAllVenueDto(@PathVariable Long venueId) {
        return serviceOne.getAllVenueDto();
    }

    @GetMapping("/event")
    public List<EventDto> getAllEventDto() {
        return serviceOne.getAllEventDto();
    }

    @GetMapping("/event/{eventId}")
    public EventDto getEventDtoById(@PathVariable Long eventId) {
        return serviceOne.getEventDtoById(eventId);
    }

    @GetMapping("/person")
    public List<PersonDto> getAllPersonDto() {
        return serviceOne.getAllPersonDto();
    }

    @GetMapping("/person/{personId}")
    public PersonDto getPersonDtoById(@PathVariable Long personId) {
        return serviceOne.getPersonDtoById(personId);
    }
}
