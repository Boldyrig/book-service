package com.gmail.fuskerr.bookservice.dao;

import com.gmail.fuskerr.bookservice.domain.Author;

import java.util.List;

public interface AuthorDao {
    List<Author> getAll();
    Author getById(long id);
    void deleteById(long id);
    long insert(Author author);
    void update(Author author);
}
