package com.example.springpostgresapp.repository;

import com.example.springpostgresapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
