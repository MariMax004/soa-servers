package com.example.labsoaservice1.application.ticket.domain;

import com.example.labsoaservice1.application.ticket.model.FilterType;
import com.example.labsoaservice1.application.ticket.model.TicketFilter;
import com.example.labsoaservice1.application.ticket.model.TicketTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor(staticName = "of")
public class VenueSpecification implements Specification<Venue>{

        private final TicketFilter filter;

    @Override
    public Predicate toPredicate(Root<Venue> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (!CollectionUtils.isEmpty(filter.getFilters())) {
            filter.getFilters().forEach(it -> {
                if (it.getName().equals("id")) {
                    if (it.getType().equals(FilterType.EQUALLY)) {
                        predicates.add(criteriaBuilder.equal(root.get(Venue_.id), it.getValue()));
                    } else if (it.getType().equals(FilterType.UNEQUALLY)) {
                        predicates.add(criteriaBuilder.notEqual(root.get(Venue_.id), it.getValue()));
                    }
                }


            });
        }
        Predicate[] predicateArray = predicates.toArray(new Predicate[0]);
        return criteriaBuilder.and(predicateArray);
    }
}


