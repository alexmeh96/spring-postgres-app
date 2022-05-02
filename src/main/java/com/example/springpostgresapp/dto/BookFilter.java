package com.example.springpostgresapp.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class BookFilter {

    private String name;
    private Double priceStart;
    private Double priceFinish;
    private Integer count;

    private LocalDate localDate;
    private LocalDateTime localDateTime;

    private List<String> categories;
    private List<Integer> codes;

    private String title;
    private Double ratingStart;
    private Double ratingFinish;
    private List<String> tags;
}
