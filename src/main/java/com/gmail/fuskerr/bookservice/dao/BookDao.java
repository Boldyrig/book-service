package com.gmail.fuskerr.bookservice.dao;

import com.gmail.fuskerr.bookservice.domain.Book;

import java.util.List;

public interface BookDao {
    List<Book> getAll();
    Book getById(long id);
    void deleteById(long id);
    long insert(Book book);
    void update(Book book);
}
