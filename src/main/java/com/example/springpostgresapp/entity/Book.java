package com.example.springpostgresapp.entity;

import com.example.springpostgresapp.model.Info;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private List<String> categories;

    @Type(type = "list-array")
    @Column(
            name = "codes",
            columnDefinition = "integer[]"
    )
    private List<Integer> codes;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb") // or, json
    private Info info;
}