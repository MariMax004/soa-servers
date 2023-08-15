package com.example.labsoaservice1.application.domain;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> {

    private int number;


    private int numberOfElements;


    private boolean first;


    private boolean last;


    private boolean hasNext;

    private boolean hasPrevious;


    private int totalPages;


    private long totalElements;

    private boolean hasContent;

    private List<T> content;


    public static <T> Page<T> of(org.springframework.data.domain.Page<T> page) {
        return Page.<T>builder()
                .number(page.getNumber())
                .numberOfElements(page.getNumberOfElements())
                .first(page.isFirst())
                .last(page.isLast())
                .hasNext(page.hasNext())
                .hasPrevious(page.hasPrevious())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .hasContent(page.hasContent())
                .content(page.getContent())
                .build();
    }

}


