package com.gmail.fuskerr.bookservice.service;

import com.gmail.fuskerr.bookservice.dao.BookDao;
import com.gmail.fuskerr.bookservice.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao dao;

    @Override
    public List<Book> getAll() {
        return dao.getAll();
    }

    @Override
    public Book getById(long id) {
        return dao.getById(id);
    }

    @Override
    public void deleteById(long id) {
        dao.deleteById(id);
    }

    @Override
    public Long insert(Book book) {
        return dao.insert(book);
    }

    @Override
    public void update(Book book) {
        dao.update(book);
    }
}
