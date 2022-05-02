package com.example.springpostgresapp.repository;

import com.example.springpostgresapp.dto.BookFilter;
import com.example.springpostgresapp.entity.Book;
import com.example.springpostgresapp.entity.QBook;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
public class BookFilterRepositoryImpl implements BookFilterRepository {

    private final EntityManager entityManager;

    @Override
    public List<Book> findAllByBookFilter(BookFilter bookFilter) {
        return null;
    }

    @Override
    public List<Book> findAllByCriteria(BookFilter filter) {

        var cb = entityManager.getCriteriaBuilder();
        var criteria = cb.createQuery(Book.class);

        var book = criteria.from(Book.class);
        criteria.select(book);

        List<Predicate> predicates = new ArrayList<>();

        if (filter.getName() != null) {
            Predicate predicate = cb.like(book.get("name"), filter.getName());
            predicates.add(predicate);
        }
        if (filter.getCount() != null) {
            Predicate predicate = cb.lessThan(book.get("count"), filter.getCount());
            predicates.add(predicate);
        }

        criteria.where(predicates.toArray(Predicate[]::new));
        return entityManager.createQuery(criteria).getResultList();
    }

    @Override
    public List<Book> findAllByQueryDsl(BookFilter filter) {

        List<com.querydsl.core.types.Predicate> predicates = new ArrayList<>();
        if (filter.getName() != null) {
            BooleanExpression eq = QBook.book.name.like(filter.getName());
            predicates.add(eq);
        }
        if (filter.getCount() != null) {
            BooleanExpression eq = QBook.book.count.loe(filter.getCount());
            predicates.add(eq);
        }

        if (filter.getCategories() != null && !filter.getCategories().isEmpty()) {
            String categories = filter.getCategories().stream().collect(Collectors.joining(",", "{", "}"));
            BooleanTemplate booleanTemplate = Expressions.booleanTemplate("my_func({0}, string_to_array({1}, ',')) = true", QBook.book.categories, categories);
            predicates.add(booleanTemplate);
        }

        com.querydsl.core.types.Predicate predicate = ExpressionUtils.allOf(predicates);
        return new JPAQuery<Book>(entityManager)
                .select(QBook.book)
                .from(QBook.book)
                .where(predicate)
                .fetch();
    }


}
