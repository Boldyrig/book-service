package com.gmail.fuskerr.bookservice.shell;

import com.gmail.fuskerr.bookservice.dao.BookDao;
import com.gmail.fuskerr.bookservice.domain.Book;
import com.gmail.fuskerr.bookservice.domain.BookShort;
import com.gmail.fuskerr.bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookServiceShellCommands {

    private final BookService service;

    @ShellMethod(key = "get-book", value = "get book from DB by id")
    public String getBook(@ShellOption({"id-long"}) long id) {
        Book book = service.getById(id);
        if(book == null) {
            return "There is no book with index = " + id;
        }
        return book.toString();
    }

    @ShellMethod(key = "get-all-book", value = "get all books from DB")
    public List<Book> getAll() {
        return service.getAll();
    }

    @ShellMethod(key = "create-book", value = "create new book (title, author-id, genre-id)")
    public String create(
            @ShellOption("--title") String title,
            @ShellOption("--author-id") long authorId,
            @ShellOption("--genre-id") long genreId
    ) {
        BookShort book = new BookShort(0, title, authorId, genreId);
        Long id = service.insert(book);
        return "Created successfully! The id of new book is " + id;
    }

    @ShellMethod(key = "delete-book", value = "delete book from DB (id)")
    public String delete(@ShellOption("--id") long id) {
        service.deleteById(id);
        return "Deleted book";
    }

    @ShellMethod(key = "update-book", value = "update book in DB (id, new-title, new-author-id, new-genre-id)")
    public String update(
            @ShellOption("--id") long id,
            @ShellOption("--new-title") String title,
            @ShellOption("--new-author-id") long authorId,
            @ShellOption("--new-genre-id") long genreId
    ) {
        BookShort book = new BookShort(id, title, authorId, genreId);
        service.update(book);
        return "Book updated";
    }
}
