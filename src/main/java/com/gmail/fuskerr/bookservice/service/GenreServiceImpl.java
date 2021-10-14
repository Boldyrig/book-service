package com.gmail.fuskerr.bookservice.service;

import com.gmail.fuskerr.bookservice.dao.GenreDao;
import com.gmail.fuskerr.bookservice.domain.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao dao;

    @Override
    public List<Genre> getAll() {
        return dao.getAll();
    }

    @Override
    public Genre getById(long id) {
        return dao.getById(id);
    }

    @Override
    public void deleteById(long id) {
        dao.deleteById(id);
    }

    @Override
    public Long insert(Genre genre) {
        return dao.insert(genre);
    }

    @Override
    public void update(Genre genre) {
        dao.update(genre);
    }
}
