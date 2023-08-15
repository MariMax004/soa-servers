package com.example.labsoaservice1.application.ticket.repo;

import com.example.labsoaservice1.application.ticket.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long>, CrudRepository<Person, Long> {
    Person getPersonById(Long id);
    @Query(value = "select *from person", nativeQuery = true)
    List<Person> getAllPearson();

}
