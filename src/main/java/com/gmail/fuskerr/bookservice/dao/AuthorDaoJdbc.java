package com.gmail.fuskerr.bookservice.dao;

import com.gmail.fuskerr.bookservice.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao{

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public List<Author> getAll() {
        return null;
    }

    @Override
    public Author getById(long id) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public long insert(Author author) {
        return 0;
    }

    @Override
    public void update(Author author) {

    }
}
