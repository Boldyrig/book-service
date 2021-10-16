package com.gmail.fuskerr.bookservice.dao;

import com.gmail.fuskerr.bookservice.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
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

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {

    private final static String ID_FIELD_NAME = "id";
    private final static String NAME_FIELD_NAME = "name";

    private final NamedParameterJdbcOperations jdbc;
    private final RowMapper<Author> authorMapper = new AuthorMapper();

    @Override
    public List<Author> getAll() {
        return jdbc.query(
                "SELECT * FROM author",
                authorMapper
        );
    }

    @Override
    public Author getById(long id) {
        try {
            return jdbc.queryForObject(
                    "SELECT * FROM author WHERE id=:id",
                    Map.of(ID_FIELD_NAME, id),
                    authorMapper
            );
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public void deleteById(long id) {
        jdbc.update(
                "DELETE FROM author WHERE id=:id",
                Map.of(ID_FIELD_NAME, id));
    }

    @Override
    public Long insert(Author author) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource params = new MapSqlParameterSource(NAME_FIELD_NAME, author.getName());
        jdbc.update(
                "INSERT INTO author (name) VALUES (:name)",
                params,
                keyHolder
        );
        return keyHolder.getKeyAs(Long.class);
    }

    @Override
    public void update(Author author) {
        Map<String, Object> params = Map.of(
                ID_FIELD_NAME, author.getId(),
                NAME_FIELD_NAME, author.getName());
        jdbc.update(
                "UPDATE author SET name=:name WHERE id=:id",
                params
        );
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong(ID_FIELD_NAME);
            String name = rs.getString(NAME_FIELD_NAME);
            return new Author(id, name);
        }
    }
}
