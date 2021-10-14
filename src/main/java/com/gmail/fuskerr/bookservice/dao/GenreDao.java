package com.gmail.fuskerr.bookservice.dao;

import com.gmail.fuskerr.bookservice.domain.Genre;

import java.util.List;

public interface GenreDao {
    List<Genre> getAll();
    Genre getById(long id);
    void deleteById(long id);
    long insert(Genre book);
    void update(Genre book);
}
