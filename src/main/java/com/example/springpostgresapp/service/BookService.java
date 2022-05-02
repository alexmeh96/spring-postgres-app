package com.example.springpostgresapp.service;

import com.example.springpostgresapp.dto.FilterDto;
import com.example.springpostgresapp.entity.Book;
import com.example.springpostgresapp.entity.QBook;
import com.example.springpostgresapp.repository.AuthorRepository;
import com.example.springpostgresapp.repository.BookRepository;
import com.example.springpostgresapp.repository.ShopRepository;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanTemplate;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ShopRepository shopRepository;

    public Book getBook(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findAll(pageable).getContent();
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> createBooks(List<Book> books) {
        return bookRepository.saveAll(books);
    }

    public Book updateBook(Long id, Book book) {
        book.setId(id);
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }



    public List<Book> bookFilterByQueryDsl(FilterDto filter) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getName() != null) {
            BooleanExpression eq = QBook.book.name.eq(filter.getName());
            predicates.add(eq);
        }
        if (!filter.getCategories().isEmpty()) {
            String categories = filter.getCategories().stream().collect(Collectors.joining(",", "{", "}"));
            BooleanTemplate booleanTemplate = Expressions.booleanTemplate("my_func({0}, string_to_array({1}, ',')) = true", QBook.book.categories, categories);
            predicates.add(booleanTemplate);
        }

        Predicate predicate = ExpressionUtils.allOf(predicates);
        if (predicate != null) {
            Iterable<Book> bookIterable = bookRepository.findAll(predicate);
            return StreamSupport.stream(bookIterable.spliterator(), false)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

}
