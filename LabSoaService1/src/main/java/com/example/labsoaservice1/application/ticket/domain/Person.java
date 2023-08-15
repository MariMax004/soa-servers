package com.example.labsoaservice1.application.ticket.domain;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="person")
@AllArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="person_seq")
    @SequenceGenerator(name="person_seq", sequenceName="person_seq", allocationSize=1)
    private long id;

    @Column(name = "name")
    private String name;



}