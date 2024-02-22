package com.example.board.repository;

import com.example.board.DTO.Board;
import com.example.board.DTO.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    List<Comment> getCommentsByBoardId(int boardId);
    Comment insertComment(Comment comment);
}
