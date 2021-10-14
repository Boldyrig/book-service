package com.gmail.fuskerr.bookservice.service;

import com.gmail.fuskerr.bookservice.domain.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAll();
    Genre getById(long id);
    void deleteById(long id);
    Long insert(Genre genre);
    void update(Genre genre);
}
