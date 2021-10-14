package com.gmail.fuskerr.bookservice.service;

import com.gmail.fuskerr.bookservice.dao.AuthorDao;
import com.gmail.fuskerr.bookservice.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao dao;

    @Override
    public List<Author> getAll() {
        return dao.getAll();
    }

    @Override
    public Author get(long id) {
        return dao.getById(id);
    }

    @Override
    public void delete(long id) {
        dao.deleteById(id);
    }

    @Override
    public Long insert(Author author) {
        return dao.insert(author);
    }

    @Override
    public void update(Author author) {
        dao.update(author);
    }
}
