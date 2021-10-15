package com.gmail.fuskerr.bookservice.service;

import com.gmail.fuskerr.bookservice.domain.Book;
import com.gmail.fuskerr.bookservice.domain.BookShort;

import java.util.List;

public interface BookService {
    List<Book> getAll();
    Book getById(long id);
    void deleteById(long id);
    Long insert(Book book);
    Long insert(BookShort book);
    void update(Book book);
    void update(BookShort book);
}
