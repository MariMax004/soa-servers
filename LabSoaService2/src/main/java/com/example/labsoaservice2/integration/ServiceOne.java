package com.example.labsoaservice2.integration;

import com.example.labsoaservice2.application.common.Endpoints;
import com.example.labsoaservice2.application.domain.Page;
import com.example.labsoaservice2.application.ticket.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "service-one", url = "${connect.service-one.url}")
public interface ServiceOne {
    @PostMapping(Endpoints.TICKET)
    TicketDto createTicket(@RequestBody TicketDto ticketDto);

    @GetMapping(Endpoints.TICKETS)
    List<TicketDto> getTicketsDto();

    @GetMapping(Endpoints.TICKET_ID)
    TicketDto getTicketDtoById(@PathVariable Long ticketId);

    @DeleteMapping(Endpoints.TICKET_ID)
    void deleteTicketDtoById(@PathVariable Long ticketId);

    @PutMapping(Endpoints.TICKET)
    TicketDto updateTicketDtoById(@RequestBody TicketDto ticketDto);

    @GetMapping(Endpoints.TICKET_MAX_NAME)
    List<TicketDto> getMaxNameTicketDto();

    @GetMapping(Endpoints.TICKET_ID_MAX)
    TicketDto getIdMaxTicketDto();

    @GetMapping(Endpoints.TICKETS_START_FROM_NAME)
    List<TicketDto> getTicketsDtoFromName(@RequestParam String name);

    @PutMapping(Endpoints.TICKET_CHANGE_TO_VIP)
    TicketDto changeTicketDtoToVip(@PathVariable Long ticketId, @PathVariable String personId);

    @PostMapping(Endpoints.TICKETS_FILTER)
    Page<TicketDto> searchVehicle(@RequestBody TicketFilter filterParams);

    @GetMapping(Endpoints.SELL_TICKET_WITH_PRICE)
    PersonDto sellTicketWithPrice(@PathVariable Long ticketId, @PathVariable Long personId, @RequestParam String price);

    @DeleteMapping(Endpoints.EVENT_DELETE)
    void cancelEvent(@PathVariable Long eventId);

    @GetMapping("/coordinates")
    List<CoordinatesDto> getCoordinatesDto();

    @GetMapping("/venue")
    List<VenueDto> getAllVenueDto();

    @GetMapping("/venue/{venueId}")
    VenueDto getAllVenueDto(@PathVariable Long venueId);

    @GetMapping("/event")
    List<EventDto> getAllEventDto();

    @GetMapping("/event/{eventId}")
    EventDto getEventDtoById(@PathVariable Long eventId);

    @GetMapping("/person")
    List<PersonDto> getAllPersonDto();

    @GetMapping("/person/{personId}")
    PersonDto getPersonDtoById(@PathVariable Long personId);

}
