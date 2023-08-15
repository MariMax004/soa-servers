package com.example.labsoaservice1.application.ticket.domain;

import com.example.labsoaservice1.application.ticket.model.LocationDto;
import lombok.*;

import javax.persistence.*;


@Data
@Entity
@Table(name="address")
@AllArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="address_seq")
    @SequenceGenerator(name="address_seq", sequenceName="address_seq", allocationSize=1)
    private long id;
    @Column(name = "street")
    private String street;
    @Column(name = "zip_code")
    private String zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location town;
}
