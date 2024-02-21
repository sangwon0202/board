package com.example.board.repository;

import com.example.board.DTO.Board;

import java.util.Optional;

public interface BoardRepository {

    Optional<Board> getBoardByBoardId(int boardId);
    Board insertBoard(Board board);

}
