package com.example.board.repository;

import com.example.board.DTO.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


@SpringBootTest
class JdbcTemplateCommentRepositoryTest {

    @Autowired
    JdbcTemplateCommentRepository jdbcTemplateCommentRepository;

    @Test
    @DisplayName("댓글 추가하고 댓글 조회하기")
    void insertCommentAndFindComment() {
        Comment comment = new Comment();
        comment.setBoardId(2);
        comment.setUserId("sangwon0202@naver.com");
        comment.setContent("좋아요");
        comment.setUploadDate(new Date());

        jdbcTemplateCommentRepository.insertComment(comment);
        Assertions.assertEquals(jdbcTemplateCommentRepository.getCommentsByBoardId(2).size(), 3);
    }
}