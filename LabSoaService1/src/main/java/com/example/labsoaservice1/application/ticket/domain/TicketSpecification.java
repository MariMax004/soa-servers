package com.example.labsoaservice1.application.ticket.domain;

import com.example.labsoaservice1.application.error.ErrorDescriptor;
import com.example.labsoaservice1.application.ticket.model.Filter;
import com.example.labsoaservice1.application.ticket.model.FilterType;
import com.example.labsoaservice1.application.ticket.model.TicketFilter;
import com.example.labsoaservice1.application.ticket.model.TicketTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@RequiredArgsConstructor(staticName = "of")
public class TicketSpecification implements Specification<Ticket> {

    private final TicketFilter filter;

    @Override
    public Predicate toPredicate(Root<Ticket> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (!CollectionUtils.isEmpty(filter.getFilters())) {
            filter.getFilters().forEach(it -> {
                if (it.getName().equals("name")) {
                    if (it.getType().equals(FilterType.EQUALLY)) {
//                        predicates.add(criteriaBuilder.equal(root.get(Ticket_.name), it.getValue()));
//                    } else if (it.getType().equals(FilterType.UNEQUALLY)) {
//                        predicates.add(criteriaBuilder.notEqual(root.get(Ticket_.name), it.getValue()));
//                    }
                        ErrorDescriptor.NOT_FOUND.throwIsTrue(it.getName().equals("name"));
                    }
                }
                if (it.getName().equals("price")) {
                    if (it.getType().equals(FilterType.EQUALLY)) {
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(Ticket_.price),
                                Double.valueOf(it.getValue())));
                    } else if (it.getType().equals(FilterType.UNEQUALLY)) {
                        predicates.add(criteriaBuilder.notEqual(root.get(Ticket_.price),
                                Double.valueOf(it.getValue())));
                    }
                }
                if (it.getName().equals("type")) {
                    if (it.getType().equals(FilterType.EQUALLY)) {
                        predicates.add(criteriaBuilder.equal(root.get(Ticket_.type),
                                TicketTypeDto.valueOf(it.getValue())));
                    } else if (it.getType().equals(FilterType.UNEQUALLY)) {
                        predicates.add(criteriaBuilder.notEqual(root.get(Ticket_.type),
                                TicketTypeDto.valueOf(it.getValue())));
                    }
                }
                if(it.getName().equals("venueId")) {
                    if (it.getType().equals(FilterType.EQUALLY)){
                        Join<Ticket, Venue> ticketVenueJoin = root.join("venue");
                        predicates.add(criteriaBuilder.equal(ticketVenueJoin.get(Venue_.id), it.getValue()));
                    }
                }

                if(it.getName().equals("coordinatesX")) {
                    Join<Ticket, Coordinates> ticketCoordinatesJoin = root.join("coordinates");
                    if (it.getType().equals(FilterType.EQUALLY)){
                        predicates.add(criteriaBuilder.equal(ticketCoordinatesJoin.get(Coordinates_.x), it.getValue()));
                    }
                    else if (it.getType().equals(FilterType.LESS_EQUALLY)) {
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(ticketCoordinatesJoin.get(Coordinates_.x),
                                Long.valueOf(it.getValue())));}
                    else if (it.getType().equals(FilterType.MORE_EQUALLY)) {
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(ticketCoordinatesJoin.get(Coordinates_.x),
                                Long.valueOf(it.getValue())));}
                }
                if(it.getName().equals("coordinatesY")) {
                    Join<Ticket, Coordinates> ticketCoordinatesJoin = root.join("coordinates");
                    if (it.getType().equals(FilterType.EQUALLY)){
                        predicates.add(criteriaBuilder.equal(ticketCoordinatesJoin.get(Coordinates_.y), it.getValue()));
                    }
                    else if (it.getType().equals(FilterType.LESS_EQUALLY)) {
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(ticketCoordinatesJoin.get(Coordinates_.y),
                                Double.valueOf(it.getValue())));}
                    else if (it.getType().equals(FilterType.MORE_EQUALLY)) {
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(ticketCoordinatesJoin.get(Coordinates_.y),
                                Double.valueOf(it.getValue())));}
                }

            });
        }
        Predicate[] predicateArray = predicates.toArray(new Predicate[0]);
        return criteriaBuilder.and(predicateArray);
    }
}
