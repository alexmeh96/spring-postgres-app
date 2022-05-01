package com.example.springpostgresapp.repository;

import com.example.springpostgresapp.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
