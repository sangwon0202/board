package com.example.board.repository;

import com.example.board.entity.User;
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
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public Optional<User> getUserByUserId(String userId) {
        List<User> result = jdbcTemplate.query("select * from user where user_id = ?", userRowMapper(), userId);
        return result.stream().findAny();
    }

    public void insertUser(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withCatalogName("board_db").withTableName("user");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("user_id", user.getUserId());
        parameters.put("password", user.getPassword());
        parameters.put("nickname", user.getNickname());

        jdbcInsert.execute(new MapSqlParameterSource(parameters));
    }

    public String getNicknameByUserId(String userId) {
        List<User> result = jdbcTemplate.query("select * from user where user_id = ?", userRowMapper(), userId);
        Optional<User> user = result.stream().findAny();
        if(user.isPresent()) return user.get().getNickname();
        else return "NULL";
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setUserId(rs.getString("user_id"));
            user.setPassword(rs.getString("password"));
            user.setNickname(rs.getString("nickname"));
            return user;
        };
    }
}


