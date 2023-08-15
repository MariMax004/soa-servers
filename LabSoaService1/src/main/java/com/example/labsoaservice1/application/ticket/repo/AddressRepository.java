package com.example.labsoaservice1.application.ticket.repo;

import com.example.labsoaservice1.application.ticket.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
    Address getAddressById(long id);
}
