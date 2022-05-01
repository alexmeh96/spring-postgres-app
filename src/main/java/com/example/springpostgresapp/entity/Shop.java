package com.example.springpostgresapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "shop", schema = "test_schema")
public class Shop {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String address;
}
