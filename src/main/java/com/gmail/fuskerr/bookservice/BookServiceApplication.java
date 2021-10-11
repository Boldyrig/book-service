package com.gmail.fuskerr.bookservice;

import com.gmail.fuskerr.bookservice.dao.BookDao;
import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;

@SpringBootApplication
public class BookServiceApplication {

    public static void main(String[] args) throws SQLException {
        ApplicationContext context = SpringApplication.run(BookServiceApplication.class, args);

        BookDao dao = context.getBean(BookDao.class);

        dao.getById(1);

        Console.main(args);
    }

}
