package com.gmail.fuskerr.bookservice.dao;

import com.gmail.fuskerr.bookservice.domain.Author;
import com.gmail.fuskerr.bookservice.domain.Book;
import com.gmail.fuskerr.bookservice.domain.BookShort;
import com.gmail.fuskerr.bookservice.domain.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {

    private final static String ID_FIELD_NAME = "id";
    private final static String NAME_FIELD_NAME = "name";
    private final static String AUTHOR_ID_FIELD_NAME = "author_id";
    private final static String GENRE_ID_FIELD_NAME = "genre_id";

    private final NamedParameterJdbcOperations jdbc;
    private final RowMapper<BookShort> bookMapper = new BookRowMapper();

    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Override
    public List<Book> getAll() {
        List<BookShort> bookShorts = jdbc.query(
                "SELECT * FROM book",
                bookMapper
        );

        Map<Long, Author> idToAuthorMap = authorDao.getAll().stream().collect(Collectors.toConcurrentMap(
                Author::getId,
                Function.identity()
        ));
        Map<Long, Genre> idToGenreMap = genreDao.getAll().stream().collect(Collectors.toConcurrentMap(
                Genre::getId,
                Function.identity()
        ));

        return bookShorts.stream().map(bookShort ->
                new Book(
                        bookShort.getId(),
                        idToAuthorMap.get(bookShort.getAuthorId()),
                        idToGenreMap.get(bookShort.getGenreId()),
                        bookShort.getTitle()
                )).toList();
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Map.of(ID_FIELD_NAME, id);

        BookShort bookShort = jdbc.queryForObject(
                "SELECT * FROM BOOK WHERE id=:id",
                params,
                bookMapper
        );

        Author author = authorDao.getById(bookShort.getAuthorId());
        Genre genre = genreDao.getById(bookShort.getGenreId());
        return new Book(
                bookShort.getId(),
                author,
                genre,
                bookShort.getTitle()
        );
    }

    @Override
    public void deleteById(long id) {
        jdbc.update(
                "DELETE FROM book WHERE id=:id",
                Map.of(ID_FIELD_NAME, id));
    }

    @Override
    public Long insert(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource params = new MapSqlParameterSource(NAME_FIELD_NAME, book.getTitle())
                .addValue(AUTHOR_ID_FIELD_NAME, book.getAuthor().getId())
                .addValue(GENRE_ID_FIELD_NAME, book.getGenre().getId());
        jdbc.update(
                "INSERT INTO book (name, author_id, genre_id) VALUES (:name, :author_id, :genre_id)",
                params,
                keyHolder
        );
        return keyHolder.getKeyAs(Long.class);
    }

    @Override
    public void update(Book book) {
        Map<String, Object> params = Map.of(
                ID_FIELD_NAME, book.getId(),
                NAME_FIELD_NAME, book.getTitle(),
                AUTHOR_ID_FIELD_NAME, book.getAuthor().getId(),
                GENRE_ID_FIELD_NAME, book.getGenre().getId());
        jdbc.update(
                "UPDATE book SET name=:name, author_id=:author_id, genre_id=:genre_id WHERE id=:id",
                params
                );
    }

    private static class BookRowMapper implements RowMapper<BookShort> {
        @Override
        public BookShort mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong(ID_FIELD_NAME);
            String name = rs.getString(NAME_FIELD_NAME);
            long author_id = rs.getLong(AUTHOR_ID_FIELD_NAME);
            long genre_id = rs.getLong(GENRE_ID_FIELD_NAME);
            return new BookShort(
                    id,
                    name,
                    author_id,
                    genre_id
            );
        }
    }
}
