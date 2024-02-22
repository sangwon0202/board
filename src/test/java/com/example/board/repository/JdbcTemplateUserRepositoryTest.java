package com.example.board.repository;

import com.example.board.DTO.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class JdbcTemplateUserRepositoryTest {

    @Autowired
    private JdbcTemplateUserRepository jdbcTemplateUserRepository;

    @Test
    @DisplayName("사용자 추가 및 사용자 조회")
    void userInsertAndUserFind() {
        User user = new User();
        user.setUserId("sangwon0202@naver.com");
        user.setPassword("0514");
        user.setNickname("상원");

        jdbcTemplateUserRepository.insertUser(user);
        Assertions.assertTrue(jdbcTemplateUserRepository.getUserByUserId(user.getUserId()).isPresent());
    }

}