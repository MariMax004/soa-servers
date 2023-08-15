package com.example.labsoaservice1.application.ticket.repo;

import com.example.labsoaservice1.application.ticket.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Long> {
    Location getLocationById(long id);

}
