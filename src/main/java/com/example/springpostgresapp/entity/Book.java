package com.example.springpostgresapp.entity;

import com.example.springpostgresapp.model.Info;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@TypeDefs({
        @TypeDef(
                name = "list-array",
                typeClass = ListArrayType.class
        ),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id", "shop", "authors"})
@ToString(exclude = {"shop", "authors"})
@Builder
@Entity
@Table(name = "book", schema = "test_schema")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private Integer count;

    private LocalDateTime timeStart;
    private LocalDateTime timeFinish;

    private LocalDate dateStart;
    private LocalDate dateFinish;

    @Type(type = "list-array")
    @Column(
            name = "categories",
            columnDefinition = "text[]"
    )
    @Builder.Default
    private List<String> categories = new ArrayList<>();

    @Type(type = "list-array")
    @Column(
            name = "codes",
            columnDefinition = "integer[]"
    )
    @Builder.Default
    private List<Integer> codes = new ArrayList<>();

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb") // or, json
    private Info info;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "books_author",
            schema = "test_schema",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors = new ArrayList<>();


    public void addAuthor(Author author) {
        authors.add(author);
        author.getBooks().add(this);
    }
}