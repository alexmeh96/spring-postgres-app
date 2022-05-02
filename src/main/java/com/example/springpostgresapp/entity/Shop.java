package com.example.springpostgresapp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "name")
@ToString(exclude = {"address", "books"})
@Builder
@Entity
@Table(name = "shop", schema = "test_schema")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(mappedBy = "shop", cascade = CascadeType.ALL)
    private Address address;

    @Builder.Default
    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();
}
