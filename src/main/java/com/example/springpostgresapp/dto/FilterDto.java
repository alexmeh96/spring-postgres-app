package com.example.springpostgresapp.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class FilterDto {
    private String name;
    @Builder.Default
    private List<String> categories = new ArrayList<>();
}
