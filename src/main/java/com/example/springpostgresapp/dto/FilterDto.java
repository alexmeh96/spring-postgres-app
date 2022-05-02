package com.example.springpostgresapp.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FilterDto {
    private String name;
    private List<String> categories;
}
