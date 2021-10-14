package com.gmail.fuskerr.bookservice.service;

import com.gmail.fuskerr.bookservice.domain.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAll();
    Author get(long id);
    void delete(long id);
    long insert(Author author);
    void update(Author author);
}
