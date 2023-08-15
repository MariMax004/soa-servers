package com.example.labsoaservice1.application.ticket.domain;


import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name="event")
@AllArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="event_seq")
    @SequenceGenerator(name="event_seq", sequenceName="event_seq", allocationSize=1)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "members")
    private String members;

}
