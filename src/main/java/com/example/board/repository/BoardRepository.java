package com.example.board.repository;

import com.example.board.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final JdbcTemplate jdbcTemplate;

    public Optional<Board> getBoardByBoardId(int boardId) {
        List<Board> result = jdbcTemplate.query("select * from board where board_id = ?", boardRowMapper(), boardId);
        return result.stream().findAny();
    }

    public void insertBoard(Board board) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withCatalogName("board_db").withTableName("board").usingGeneratedKeyColumns("board_id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("user_id", board.getUserId());
        parameters.put("title", board.getTitle());
        parameters.put("content", board.getContent());
        parameters.put("upload_date", board.getUploadDate());

        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        board.setBoardId(key.intValue());
    }

    public List<Board> getBoardListAboutAll() {
        List<Board> result = jdbcTemplate.query("select * from board", boardRowMapper());
        return result;
    }

    private RowMapper<Board> boardRowMapper() {
        return (rs, rowNum) -> {
            Board board = new Board();
            board.setBoardId(rs.getInt("board_id"));
            board.setUserId(rs.getString("user_id"));
            board.setTitle(rs.getString("title"));
            board.setContent(rs.getString("content"));
            board.setUploadDate(rs.getDate("upload_date"));
            return board;
        };
    }
}
