package com.example.labsoaservice1.application.ticket.domain;

import com.example.labsoaservice1.application.ticket.model.AddressDto;
import lombok.*;

import javax.persistence.*;


@Data
@Entity
@Table(name="venue")
@AllArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="venue_seq")
    @SequenceGenerator(name="venue_seq", sequenceName="venue_seq", allocationSize=1)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "capacity")
    private int capacity;

//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "address_id")
//    private Address address;
}
