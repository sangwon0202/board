package com.example.board.service;


import com.example.board.DTO.BoardUploadDto;
import com.example.board.entity.Board;
import com.example.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class BoardUploadService {


    private final BoardRepository boardRepository;


    public void boardUpload(BoardUploadDto boardUploadDto) {
        Board board = new Board();
        board.setUploadDate(new Date());
        board.setTitle(boardUploadDto.getTitle());
        board.setContent(boardUploadDto.getContent());
        board.setUserId(boardUploadDto.getUserId());

        boardRepository.insertBoard(board);
    }
}
