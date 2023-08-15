package com.example.labsoaservice1.application.ticket.repo;

import com.example.labsoaservice1.application.ticket.domain.Coordinates;
import com.example.labsoaservice1.application.ticket.domain.Ticket;
import com.example.labsoaservice1.application.ticket.domain.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VenueRepository extends JpaRepository<Venue,Long>, JpaSpecificationExecutor<Venue> {
    Venue getVenueById(long id);
    @Query(value = "select *from venue", nativeQuery = true)
    List<Venue> getAllVenue();
}
