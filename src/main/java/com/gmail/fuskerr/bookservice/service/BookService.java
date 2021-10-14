package com.gmail.fuskerr.bookservice.service;

import com.gmail.fuskerr.bookservice.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();
    Book getById(long id);
    void deleteById(long id);
    long insert(Book book);
    void update(Book book);
}
