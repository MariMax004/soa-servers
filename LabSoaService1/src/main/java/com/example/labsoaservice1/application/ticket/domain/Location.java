package com.example.labsoaservice1.application.ticket.domain;
import lombok.*;

import javax.persistence.*;



@Data
@Entity
@Table(name="location")
@AllArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="location_seq")
    @SequenceGenerator(name="location_seq", sequenceName="location_seq", allocationSize=1)
    private long id;
    @Column(name = "x")
    private long x;
    @Column(name = "y")
    private Double y;
    @Column(name = "z")
    private float z;
    @Column(name = "name")
    private String name;
}
