package com.example.board.service;

import com.example.board.DTO.Board;
import com.example.board.DTO.BoardInfo;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.CommentRepository;
import com.example.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardInfoListService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public List<BoardInfo> getBoardInfoListAboutAll() {
        List<Board> boards = boardRepository.getAllBoards();
        List<BoardInfo> boardInfoList = boards.stream().map(board -> {

            String nickName = userRepository.transformUserIdToNickname(board.getUserId());
            int commentCount = commentRepository.getCommentsByBoardId(board.getBoardId()).size();

            BoardInfo boardInfo = new BoardInfo();
            boardInfo.setBoardId(board.getBoardId());
            boardInfo.setNickname(nickName);
            boardInfo.setTitle(board.getTitle());
            boardInfo.setUploadDate(board.getUploadDate());
            boardInfo.setCommentCount(commentCount);

            return boardInfo;
        }).toList();

        return boardInfoList;
    }



}
