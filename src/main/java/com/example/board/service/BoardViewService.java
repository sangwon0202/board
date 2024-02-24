package com.example.board.service;


import com.example.board.DTO.BoardViewDto;
import com.example.board.DTO.CommentViewDto;
import com.example.board.entity.Board;
import com.example.board.entity.Comment;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.CommentRepository;
import com.example.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardViewService {


    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public BoardViewDto getBoardViewByBoardId(int boardId) {
        BoardViewDto boardView = new BoardViewDto();
        Board board = boardRepository.getBoardByBoardId(boardId).get();
        String nickname = userRepository.getNicknameByUserId(board.getUserId());

        boardView.setBoardId(boardId);
        boardView.setTitle(board.getTitle());
        boardView.setNickname(nickname);
        boardView.setUploadDate(board.getUploadDate());
        boardView.setContent(board.getContent());

        List<CommentViewDto> commentViewDtoList = new ArrayList<>();
        List<Comment> commentList = commentRepository.getCommentListByBoardId(boardId);

        for(Comment comment : commentList) {
            CommentViewDto commentViewDto = new CommentViewDto();
            String commentNickname = userRepository.getNicknameByUserId(comment.getUserId());

            commentViewDto.setNickname(commentNickname);
            commentViewDto.setUploadDate(comment.getUploadDate());
            commentViewDto.setContent(comment.getContent());

            commentViewDtoList.add(commentViewDto);
        }
        boardView.setCommentList(commentViewDtoList);

        return boardView;
    }


}
