package com.example.board.service;

import com.example.board.DTO.BoardMetaDto;
import com.example.board.entity.Board;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.CommentRepository;
import com.example.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardListService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public List<BoardMetaDto> getBoardListAboutAll() {

        List<Board> boardList = boardRepository.getBoardListAboutAll();

        List<BoardMetaDto> boardMetaDtoList = new ArrayList<>();

        for(Board board : boardList) {
            BoardMetaDto boardMetaData = new BoardMetaDto();
            String nickname = userRepository.getNicknameByUserId(board.getUserId());
            int commentCount = commentRepository.getCommentListByBoardId(board.getBoardId()).size();

            boardMetaData.setBoardId(board.getBoardId());
            boardMetaData.setTitle(board.getTitle());
            boardMetaData.setCommentCount(commentCount);
            boardMetaData.setNickname(nickname);
            boardMetaData.setUploadDate(board.getUploadDate());

            boardMetaDtoList.add(boardMetaData);
        }

        return boardMetaDtoList;
    }

}
