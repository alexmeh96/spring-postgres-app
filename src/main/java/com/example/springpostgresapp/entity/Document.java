package com.example.springpostgresapp.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"name", "number"})
@ToString(exclude = "author")
@Builder
@Entity
@Table(name = "document", schema = "test_schema")
public class Document {

    @Id
    @Column(name = "author_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Author author;

    private String name;
    private Integer number;
}
