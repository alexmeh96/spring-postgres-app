package com.example.springpostgresapp.repository;

import com.example.springpostgresapp.dto.BookFilter;
import com.example.springpostgresapp.entity.Book;

import java.util.List;

public interface BookFilterRepository {

    List<Book> findAllByBookFilter(BookFilter bookFilter);

    List<Book> findAllByCriteria(BookFilter bookFilter);

    List<Book> findAllByQueryDsl(BookFilter bookFilter);
}