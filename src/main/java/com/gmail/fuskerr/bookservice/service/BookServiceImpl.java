package com.gmail.fuskerr.bookservice.service;

import com.gmail.fuskerr.bookservice.dao.BookDao;
import com.gmail.fuskerr.bookservice.domain.Author;
import com.gmail.fuskerr.bookservice.domain.Book;
import com.gmail.fuskerr.bookservice.domain.BookShort;
import com.gmail.fuskerr.bookservice.domain.Genre;
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
    public Long insert(BookShort book) {
        return insert(new Book(
                        book.getId(),
                        new Author(book.getAuthorId(), ""),
                        new Genre(book.getGenreId(), ""),
                        book.getTitle()
                ));
    }

    @Override
    public void update(Book book) {
        dao.update(book);
    }

    @Override
    public void update(BookShort book) {
        update(new Book(
                book.getId(),
                new Author(book.getAuthorId(), ""),
                new Genre(book.getGenreId(), ""),
                book.getTitle()
        ));
    }
}
