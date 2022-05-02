package com.example.springpostgresapp.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Builder.Default
    private List<String> categories = new ArrayList<>();
    @Builder.Default
    private List<Integer> codes = new ArrayList<>();

    private String title;
    private Double ratingStart;
    private Double ratingFinish;
    @Builder.Default
    private List<String> tags = new ArrayList<>();
}
