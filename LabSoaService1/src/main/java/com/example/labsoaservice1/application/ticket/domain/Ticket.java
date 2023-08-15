package com.example.labsoaservice1.application.ticket.domain;

import com.example.labsoaservice1.application.ticket.model.CoordinatesDto;
import com.example.labsoaservice1.application.ticket.model.TicketTypeDto;
import com.example.labsoaservice1.application.ticket.model.VenueDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name="ticket")
@AllArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ticket_seq")
    @SequenceGenerator(name="ticket_seq", sequenceName="ticket_seq", allocationSize=1)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coordinates_id")
    private Coordinates coordinates;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "price")
    private Double price;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TicketTypeDto type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id")
    private Venue venue;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person person;
}
