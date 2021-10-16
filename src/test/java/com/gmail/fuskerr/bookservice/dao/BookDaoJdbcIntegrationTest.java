package com.gmail.fuskerr.bookservice.dao;

import com.gmail.fuskerr.bookservice.domain.Author;
import com.gmail.fuskerr.bookservice.domain.Book;
import com.gmail.fuskerr.bookservice.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

@DisplayName("Тестирование BookDaoJdbc")
@JdbcTest
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})
class BookDaoJdbcIntegrationTest {
    private final static String EXPECT_BOOK_TITLE1 = "test1";
    private final static String EXPECT_BOOK_TITLE2 = "test2";
    private final static String EXPECT_BOOK_TITLE3 = "test3";

    private final static Book NEW_BOOK = new Book(
            1,
            new Author(0, null),
            new Genre(0, null),
            "test4"
    );

    @Autowired
    BookDaoJdbc dao;

    @DisplayName("Когда вызываем метод getAll() - возвращаются все книги")
    @Test
    void whenCallMethodGetAll_thenShouldReturnAllBooks() {
        List<Book> books = dao.getAll();
        assertThat(books.size()).isEqualTo(3);
        assertThat(books.get(0).getTitle()).isEqualTo(EXPECT_BOOK_TITLE1);
        assertThat(books.get(1).getTitle()).isEqualTo(EXPECT_BOOK_TITLE2);
        assertThat(books.get(2).getTitle()).isEqualTo(EXPECT_BOOK_TITLE3);
    }

    @DisplayName("Когда вызываем метод getById(1) - возвращается первая книга")
    @Test
    void whenCallGetById1_thenShouldReturnFirstBook() {
        Book book = dao.getById(1);
        assertThat(book.getTitle()).isEqualTo(EXPECT_BOOK_TITLE1);
    }

    @DisplayName("Когда вызываем метод deleteById(1) - удаляется первая книга")
    @Test
    void whenCallDeleteById1_thenShouldDeleteFirstBook() {
        dao.deleteById(1);
        List<Book> books = dao.getAll();
        assertThat(books.size()).isEqualTo(2);
        assertThat(books.get(0).getTitle()).isEqualTo(EXPECT_BOOK_TITLE2);
        assertThat(books.get(1).getTitle()).isEqualTo(EXPECT_BOOK_TITLE3);
    }

    @DisplayName("Когда вызываем метод insert - добавляется новая книга")
    @Test
    void whenCallInsert_thenShouldInsertNewBook() {
        dao.insert(NEW_BOOK);
        List<Book> books = dao.getAll();
        assertThat(books.size()).isEqualTo(4);
        assertThat(books.get(3).getTitle()).isEqualTo(NEW_BOOK.getTitle());
    }

    @DisplayName("Когда вызываем метод update - обновляется существующая книга")
    @Test
    void whenCallUpdate_thenShouldUpdateExistBook() {
        dao.update(NEW_BOOK);
        List<Book> books = dao.getAll();
        assertThat(books.get(0).getTitle()).isEqualTo(NEW_BOOK.getTitle());
    }
}