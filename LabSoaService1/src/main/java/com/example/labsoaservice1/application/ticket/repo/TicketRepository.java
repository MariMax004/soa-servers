package com.example.labsoaservice1.application.ticket.repo;

import com.example.labsoaservice1.application.ticket.domain.Coordinates;
import com.example.labsoaservice1.application.ticket.domain.Event;
import com.example.labsoaservice1.application.ticket.domain.Ticket;
import com.example.labsoaservice1.application.ticket.domain.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository  extends JpaRepository<Ticket, Long>, JpaSpecificationExecutor<Ticket> {
    Ticket getById(long id);
    @Query(value = "select *from ticket", nativeQuery = true)
    List<Ticket> getAllTickets();
    void deleteById(Long id);
    @Query(value = "select *from ticket where char_length(name)=(select max(char_length(name)) from ticket)", nativeQuery = true)
    List<Ticket> getTicketWithMaxName();
    Ticket findTicketByName(String name);
    Long countAllByNameStartingWith(String name);
    List<Ticket> findAllByNameStartingWith(String name);
    List<Ticket> findAllByEventId(Long id);
    void deleteAllByEvent(Event event);

}
