package com.example.labsoaservice1.application.ticket.service;

import com.example.labsoaservice1.application.domain.Page;
import com.example.labsoaservice1.application.error.ErrorDescriptor;
import com.example.labsoaservice1.application.ticket.domain.*;
import com.example.labsoaservice1.application.ticket.model.*;
import com.example.labsoaservice1.application.ticket.repo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final CoordinatesRepository coordinatesRepository;
    private final LocationRepository locationRepository;
    private final AddressRepository addressRepository;
    private final VenueRepository venueRepository;
    private final EventRepository eventRepository;
    private final PersonRepository personRepository;

    @Override
    public TicketDto getTicket(long id) {
        ErrorDescriptor.TICKET_NOT_FOUND.throwIsFalse(ticketRepository.existsById(id));
        return convertToTicketDto(ticketRepository.getById(id));
    }

    @Override
    public List<TicketDto> getAllTickets() {
        return ticketRepository.getAllTickets().stream().map(this::convertToTicketDto)
                .sorted(Comparator.comparingLong(TicketDto::getId))
                .collect(Collectors.toList());
    }

    @Override
    public void addTicket(TicketDto ticketDto) {
        ticketRepository.save(convertToTicket(ticketDto));
    }

    @Override
    public void updateTicket(TicketDto ticketDto) {
        if (!ObjectUtils.isEmpty(ticketDto.getId()) && ticketRepository.existsById(ticketDto.getId()))
            ticketRepository.save(convertToTicket(ticketDto));
        else {
            ErrorDescriptor.TICKET_NOT_FOUND.throwIsTrue(ObjectUtils.isEmpty(ticketDto.getId()));
        }
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public List<TicketDto> getMaxNameTicketDto() {
        return ticketRepository.getTicketWithMaxName().stream().map(this::convertToTicketDto)
                .sorted(Comparator.comparingLong(TicketDto::getId))
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketDto> getTicketsDtoFromName(String name) {
        List<TicketDto> tickets = new java.util.ArrayList<>(Collections.emptyList());
        log.info("name({})", name);
        log.info("name({})", ticketRepository.countAllByNameStartingWith(name));
        if (ticketRepository.countAllByNameStartingWith(name) >= 1) {
            tickets = ticketRepository.findAllByNameStartingWith(name).stream().map(this::convertToTicketDto)
                    .sorted(Comparator.comparingLong(TicketDto::getId))
                    .collect(Collectors.toList());

        }
        return tickets;
    }

    @Transactional
    @Override
    public void cancelEvent(Long id) {
        ErrorDescriptor.EVENT_NOT_FOUND.throwIsFalse(!ObjectUtils.isEmpty(eventRepository.getEventById(id)));
        if (!ObjectUtils.isEmpty(eventRepository.getEventById(id))) {
            log.info("delete({})", eventRepository.getEventById(id));
            ticketRepository.deleteAllByEvent(eventRepository.getEventById(id));
            eventRepository.deleteById(id);
        }

    }

    @Override
    public TicketDto sellTicketWithPrice(Long ticketId, Long personId, Double price) {
        Ticket ticket;
        Person person;
        ErrorDescriptor.TICKET_NOT_FOUND.throwIsFalse(ticketRepository.existsById(ticketId));
        ErrorDescriptor.PERSON_NOT_FOUND.throwIsFalse(personRepository.existsById(personId));
        ticket = ticketRepository.getById(ticketId);
        ticket.setPrice(price);
        person = personRepository.getPersonById(personId);
        ticket.setPerson(person);
        ticketRepository.save(ticket);

        return convertToTicketDto(ticket);
    }

    @Override
    public Page<TicketDto> searchTicket(TicketFilter filterParams) {
        Sort.Order order = !ObjectUtils.isEmpty(filterParams.getSort()) && (filterParams.getSort().getSortType() != null) ?
                filterParams.getSort().getSortType().equals(SortType.LESS_TO_MORE)
                        ? Sort.Order.asc(filterParams.getSort().getName())
                        : Sort.Order.desc(filterParams.getSort().getName())
                : Sort.Order.asc("id");
        PageRequest pageable = PageRequest.of(filterParams.getPage() - 1, filterParams.getLimit(),
                Sort.by(order));
        val entities = ticketRepository.findAll(TicketSpecification.of(filterParams), pageable)
                .map(this::convertToTicketDto);
        return Page.of(entities);
    }

    @Override
    public List<CoordinatesDto> getCoordinates() {
        return coordinatesRepository.getAllCoordinates().stream().map(this::convertToCoordinatesDto)
                .sorted(Comparator.comparingLong(CoordinatesDto::getId))
                .collect(Collectors.toList());
    }

    @Override
    public List<VenueDto> getAllVenueDto() {
        return venueRepository.getAllVenue().stream().map(this::converToVenueDto)
                .sorted(Comparator.comparingLong(VenueDto::getId))
                .collect(Collectors.toList());
    }

    @Override
    public List<EventDto> getAllEventDto() {
        return eventRepository.getAllEvent().stream().map(this::converToEventDto)
                .sorted(Comparator.comparingLong(EventDto::getId))
                .collect(Collectors.toList());

    }

    @Override
    public List<PersonDto> getAllPearsonDto() {
        return personRepository.getAllPearson().stream().map(this::converToPersonDto)
                .sorted(Comparator.comparingLong(PersonDto::getId))
                .collect(Collectors.toList());
    }

    @Override
    public PersonDto getPersonById(Long personId) {
        return converToPersonDto(personRepository.getPersonById(personId));
    }

    @Override
    public EventDto getEventDtoById(Long eventId) {
        return converToEventDto(eventRepository.getEventById(eventId));
    }

    @Override
    public VenueDto getVenueDtoById(Long venueId) {
        return converToVenueDto(venueRepository.getVenueById(venueId));
    }

    private TicketDto convertToTicketDto(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setId((ticket.getId()));
        ticketDto.setName(ticket.getName());
        ticketDto.setPrice(ticket.getPrice());
        ticketDto.setCreationDate(ticket.getCreationDate());
        ticketDto.setType(ticket.getType());
        ticketDto.setPerson(converToPersonDto(ticket.getPerson()));
//        if (!ObjectUtils.isEmpty(ticketDto.getCoordinates())) {
        ticketDto.setCoordinates(convertToCoordinatesDto(ticket.getCoordinates()));
//        }

        ticketDto.setVenue(converToVenueDto(venueRepository.getVenueById(ticket.getVenue().getId())));
        ticketDto.setEvent(converToEventDto(eventRepository.getEventById(ticket.getEvent().getId())));
        return ticketDto;
    }

    private CoordinatesDto convertToCoordinatesDto(Coordinates coordinates) {
        CoordinatesDto coordinatesDto = new CoordinatesDto();
        coordinatesDto.setX(coordinates.getX());
        coordinatesDto.setY(coordinates.getY());
        return coordinatesDto;
    }

    private VenueDto converToVenueDto(Venue venue) {
        VenueDto venueDto = new VenueDto();
        venueDto.setId(venue.getId());
        venueDto.setName(venue.getName());
        venueDto.setCapacity(venue.getCapacity());
        return venueDto;
    }


    private EventDto converToEventDto(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setId(event.getId());
        eventDto.setName(event.getName());
        eventDto.setMembers(event.getMembers());
        return eventDto;
    }

    private PersonDto converToPersonDto(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setName(person.getName());
        return personDto;
    }

    private LocationDto convertToLocationDto(Location location) {
        LocationDto locationDto = new LocationDto();
        locationDto.setName(location.getName());
        locationDto.setX(location.getX());
        locationDto.setY(location.getY());
        return locationDto;
    }

    private AddressDto convertToAddressDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setStreet(address.getStreet());
        addressDto.setZipCode(address.getZipCode());
        addressDto.setTown(convertToLocationDto(locationRepository.getLocationById(address.getTown().getId())));
        return addressDto;
    }

    private Ticket convertToTicket(TicketDto ticketDto) {
        Ticket ticket = new Ticket();
        Person person = new Person();
        Coordinates coordinates = new Coordinates();
        if (!ObjectUtils.isEmpty(ticketDto.getId())) {
            ticket.setId(ticketDto.getId());
            person = (ticketRepository.getById(ticket.getId()).getPerson());
            coordinates = (ticketRepository.getById(ticket.getId()).getCoordinates());
        }

        person.setName(ticketDto.getPerson().getName());
        personRepository.save(person);
        coordinates.setX(ticketDto.getCoordinates().getX());
        coordinates.setY(ticketDto.getCoordinates().getY());
        coordinatesRepository.save(coordinates);
        ErrorDescriptor.NAME_NOT_NULL.throwIsTrue(ObjectUtils.isEmpty(ticketDto.getName()));
        ticket.setName(ticketDto.getName());
        ErrorDescriptor.PRICE_NOT_NULL.throwIsTrue(ObjectUtils.isEmpty(ticketDto.getPrice()));
        ticket.setPrice(ticketDto.getPrice());
        ticket.setCreationDate(LocalDateTime.now());
        ticket.setType(ticketDto.getType());
        ticket.setPerson(person);
        ticket.setCoordinates(coordinates);
        ticket.setVenue(venueRepository.getVenueById(ticketDto.getVenue().getId()));
        ErrorDescriptor.EVENT_NOT_FOUND.throwIsTrue(ObjectUtils.isEmpty(ticketDto.getEvent().getId()));
        ticket.setEvent(eventRepository.getEventById(ticketDto.getEvent().getId()));
        return ticket;
    }
}
