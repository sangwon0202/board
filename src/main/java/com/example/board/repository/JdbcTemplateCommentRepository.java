package com.example.board.repository;

import com.example.board.DTO.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class JdbcTemplateCommentRepository implements CommentRepository {

    private final JdbcTemplate jdbcTemplate;
    @Override
    public List<Comment> getCommentsByBoardId(int boardId) {
        List<Comment> result = jdbcTemplate.query("select * from comment where board_id = ?", commentRowMapper(), boardId);
        return result;
    }

    @Override
    public Comment insertComment(Comment comment) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withCatalogName("board_db").withTableName("comment").usingGeneratedKeyColumns("comment_id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("board_id", comment.getBoardId());
        parameters.put("user_id", comment.getUserId());
        parameters.put("content", comment.getContent());
        parameters.put("upload_date", comment.getUploadDate());

        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        comment.setCommentId(key.intValue());
        return comment;
    }

    private RowMapper<Comment> commentRowMapper() {
        return (rs, rowNum) -> {
            Comment comment = new Comment();
            comment.setCommentId(rs.getInt("comment_id"));
            comment.setBoardId(rs.getInt("board_id"));
            comment.setUserId(rs.getString("user_id"));
            comment.setContent(rs.getString("content"));
            comment.setUploadDate(rs.getDate("upload_date"));
            return comment;
        };
    }

}


