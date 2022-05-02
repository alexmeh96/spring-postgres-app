package com.example.springpostgresapp.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Info {

    private String title;
    private Double rating;

    private List<String> tags;
}
