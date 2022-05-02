package com.example.springpostgresapp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"name", "age"})
@ToString(exclude = {"document", "books"})
@Builder
@Entity
@Table(name = "author", schema = "test_schema")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;

    @OneToOne(mappedBy = "author", cascade = CascadeType.ALL)
    private Document document;

    @Builder.Default
    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();
}
