package com.example.labsoaservice1.application.ticket.repo;

import com.example.labsoaservice1.application.ticket.domain.Coordinates;
import com.example.labsoaservice1.application.ticket.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    Event getEventById(Long id);
    void deleteById(Long id);
    @Query(value = "select *from event", nativeQuery = true)
    List<Event> getAllEvent();
}
