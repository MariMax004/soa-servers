package com.example.labsoaservice1.application.ticket.repo;

import com.example.labsoaservice1.application.ticket.domain.Coordinates;
import com.example.labsoaservice1.application.ticket.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CoordinatesRepository extends JpaRepository<Coordinates, Long> {
    Coordinates getCoordinatesById(long id);
    @Query(value = "select *from coordinates", nativeQuery = true)
    List<Coordinates> getAllCoordinates();
}
