package com.example.board.repository;

import com.example.board.DTO.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class JdbcTemplateBoardRepositoryTest {

    @Autowired
    private JdbcTemplateBoardRepository jdbcTemplateBoardRepository;

    @Test
    @DisplayName("게시판 추가 및 게시판 아이디로 조회")
    void insertAndFindByBoardId() {
        Board board = new Board();
        board.setUserId("jui91178@naver.com");
        board.setTitle("테스트 제목");
        board.setContent("테스트 내용");
        board.setUploadDate(new Date());
        jdbcTemplateBoardRepository.insertBoard(board);
        assertTrue(jdbcTemplateBoardRepository.getBoardByBoardId(board.getBoardId()).isPresent());

    }
}