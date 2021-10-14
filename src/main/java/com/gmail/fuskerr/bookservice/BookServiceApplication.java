package com.gmail.fuskerr.bookservice;

import com.gmail.fuskerr.bookservice.dao.BookDao;
import com.gmail.fuskerr.bookservice.domain.Author;
import com.gmail.fuskerr.bookservice.domain.Book;
import com.gmail.fuskerr.bookservice.domain.Genre;
import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class BookServiceApplication {

    public static void main(String[] args) throws SQLException {
        ApplicationContext context = SpringApplication.run(BookServiceApplication.class, args);

        BookDao dao = context.getBean(BookDao.class);

        dao.insert(new Book(123L, new Author(123L, "Author"), new Genre(12322L, "Horror"), "title book"));
        dao.update(new Book(1, new Author(111L, "authro"), new Genre(222L, "genre"), "Alicoxxx"));

        List<Book> books = dao.getAll();

        Console.main(args);
    }

}
