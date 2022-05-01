package com.example.springpostgresapp.service;

import com.example.springpostgresapp.entity.Book;
import com.example.springpostgresapp.repository.AuthorRepository;
import com.example.springpostgresapp.repository.BookRepository;
import com.example.springpostgresapp.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ShopRepository shopRepository;

    private Optional<Book> getBook(Long id) {
        return bookRepository.findById(id);
    }

    private List<Book> getBooks() {
        return bookRepository.findAll();
    }

    private List<Book> getBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findAll(pageable).getContent();
    }

    private Book createBook(Book book) {
        return bookRepository.save(book);
    }

    private List<Book> createBooks(List<Book> books) {
        return bookRepository.saveAll(books);
    }

    private Book updateBook(Long id, Book book) {
        book.setId(id);
        return bookRepository.save(book);
    }

    private void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

}
