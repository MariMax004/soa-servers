package com.example.labsoaservice1.application.ticket.domain;


import lombok.*;

import javax.persistence.*;


@Data
@Entity
@Table(name="coordinates")
@AllArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="coordinates_seq")
    @SequenceGenerator(name="coordinates_seq", sequenceName="coordinates_seq", allocationSize=1)
    private long id;

    @Column(name = "x")
    private long x;

    @Column(name = "y")
    private double y;
}
