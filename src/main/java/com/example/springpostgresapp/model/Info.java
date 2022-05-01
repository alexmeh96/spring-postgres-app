package com.example.springpostgresapp.model;

import lombok.Data;

import java.util.List;

@Data
public class Info {

    private String title;
    private Double rating;

    private List<String> tags;
}
