package com.example.springpostgresapp.repository;

import com.example.springpostgresapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface BookRepository extends
        JpaRepository<Book, Long>,
        QuerydslPredicateExecutor<Book>,
        BookFilterRepository {

    @Query(value = "SELECT b.* FROM test_schema.book b "
            + "WHERE b.info ->> 'title' = ?1",
            nativeQuery = true)
    Book findBookByInfoTitle(String title);

    @Query(value = "SELECT b.* FROM test_schema.book b "
            + "WHERE CAST(b.info ->> 'rating' AS numeric) = ?1",
            nativeQuery = true)
    Book findBookByInfoRating(Double rating);

    @Query(value = "SELECT * FROM test_schema.book b " +
            "WHERE b.categories && CAST(:categories AS text[]) ", nativeQuery = true)
    List<Book> findBookByCategory(String categories);


    @Query(value = "SELECT b FROM Book b " +
            "WHERE (:name is null or b.name = :name) and (:count is null or b.count > :count)")
    List<Book> findBookWithNull(String name, Integer count);

}
