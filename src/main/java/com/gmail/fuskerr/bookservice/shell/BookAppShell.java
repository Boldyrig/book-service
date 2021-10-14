package com.gmail.fuskerr.bookservice.shell;

import com.gmail.fuskerr.bookservice.dao.BookDao;
import com.gmail.fuskerr.bookservice.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class BookAppShell {

    private final BookDao bookDao;

    @ShellMethod(key = "get-book", value = "get book from DB by id")
    public String shell(@ShellOption({"id-long"}) long id) {
        Book book = bookDao.getById(id);
        if(book == null) {
            return "There is no book with index = " + id;
        }
        return book.toString();
    }
}
