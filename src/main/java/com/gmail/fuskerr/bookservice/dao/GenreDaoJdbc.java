package com.gmail.fuskerr.bookservice.dao;

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

@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {

    private final static String ID_FIELD_NAME = "id";
    private final static String NAME_FIELD_NAME = "name";

    private final NamedParameterJdbcOperations jdbc;
    private final RowMapper<Genre> genreMapper = new GenreMapper();

    @Override
    public List<Genre> getAll() {
        return jdbc.query(
                "SELECT * FROM genre",
                genreMapper
        );
    }

    @Override
    public Genre getById(long id) {
        return jdbc.queryForObject(
                "SELECT * FROM genre WHERE id=:id",
                Map.of(ID_FIELD_NAME, id),
                genreMapper
        );
    }

    @Override
    public void deleteById(long id) {
        jdbc.update(
                "DELETE FROM genre WHERE id=:id",
                Map.of(ID_FIELD_NAME, id));
    }

    @Override
    public Long insert(Genre genre) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource params = new MapSqlParameterSource(NAME_FIELD_NAME, genre.getName());
        jdbc.update(
                "INSERT INTO genre (name) VALUES (:name)",
                params,
                keyHolder
        );
        return keyHolder.getKeyAs(Long.class);
    }

    @Override
    public void update(Genre genre) {
        Map<String, Object> params = Map.of(
                ID_FIELD_NAME, genre.getId(),
                NAME_FIELD_NAME, genre.getName());
        jdbc.update(
                "UPDATE genre SET name=:name WHERE id=:id",
                params
        );
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong(ID_FIELD_NAME);
            String name = rs.getString(NAME_FIELD_NAME);
            return new Genre(id, name);
        }
    }
}
