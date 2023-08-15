package com.example.labsoaservice2.application.domain;

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


}


