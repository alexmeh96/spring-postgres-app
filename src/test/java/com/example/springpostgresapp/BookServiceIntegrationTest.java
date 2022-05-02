package com.example.springpostgresapp;

import com.example.springpostgresapp.entity.Book;
import com.example.springpostgresapp.model.Info;
import com.example.springpostgresapp.repository.BookRepository;
import com.example.springpostgresapp.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookServiceIntegrationTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    Book book1 = Book.builder()
            .name("book1")
            .price(55.23)
            .count(3)
            .timeStart(LocalDateTime.now().minusYears(5))
            .timeFinish(LocalDateTime.now())
            .dateStart(LocalDate.now().minusYears(5))
            .dateFinish(LocalDate.now())
            .categories(List.of("category1", "category2", "category3"))
            .codes(List.of(3452, 4532, 5734))
            .info(Info.builder()
                    .title("title1")
                    .rating(4.7)
                    .tags(List.of("tag1", "tag2", "tag3"))
                    .build())
            .build();

    Book book2 = Book.builder()
            .name("book2")
            .price(565.23)
            .count(5)
            .timeStart(LocalDateTime.now().minusYears(3))
            .timeFinish(LocalDateTime.now())
            .dateStart(LocalDate.now().minusYears(3))
            .dateFinish(LocalDate.now())
            .categories(List.of("category11", "category22", "category33"))
            .codes(List.of(5647, 987, 4325))
            .info(Info.builder()
                    .title("title2")
                    .rating(3.7)
                    .tags(List.of("tag11", "tag22", "tag33"))
                    .build())
            .build();

    Book book3 = Book.builder()
            .name("book3")
            .price(23.0)
            .count(8)
            .timeStart(LocalDateTime.now().minusYears(1))
            .timeFinish(LocalDateTime.now())
            .dateStart(LocalDate.now().minusYears(1))
            .dateFinish(LocalDate.now())
            .categories(List.of("category111", "category222", "category333"))
            .codes(List.of(8975, 123489, 4950))
            .info(Info.builder()
                    .title("title3")
                    .rating(4.1)
                    .tags(List.of("tag111", "tag222", "tag333"))
                    .build())
            .build();

    List<Book> books = List.of(book1, book2, book3);

    @Test
    void test1() {
        Book bk = bookService.createBook(book1);
        assertNotNull(bk);
    }

    @Test
    void test2() {
        List<Book> bookList = bookService.createBooks(books);
        assertEquals(3, bookList.size());
    }

    @Test
    void test3() {
        Book book = bookService.getBook(1L);
        assertNotNull(book);
    }

    @Test
    void test4() {
        List<Book> books = bookService.getBooks();
        assertNotEquals(0, books.size());
    }

    @Test
    void test5() {
        Book book = bookRepository.findBookByInfoTitle("title2");
        assertEquals("book2", book.getName());

        book = bookRepository.findBookByInfoRating(4.1);
        assertEquals("book3", book.getName());

        String collect = Stream.of("category1", "category333").collect(joining(",", "{", "}"));

        List<Book> bookList = bookRepository.findBookByCategory(collect);
        assertEquals(2, bookList.size());
    }
}
