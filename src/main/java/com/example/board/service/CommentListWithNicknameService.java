package com.example.board.service;

import com.example.board.DTO.Board;
import com.example.board.DTO.BoardInfo;
import com.example.board.DTO.Comment;
import com.example.board.DTO.CommentWithNickname;
import com.example.board.repository.CommentRepository;
import com.example.board.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentListWithNicknameService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public List<CommentWithNickname> getCommentListWithNickname(int boardId) {
        List<Comment> commentList = commentRepository.getCommentsByBoardId(boardId);
        List<CommentWithNickname> commentListWithNickname = commentList.stream().map(comment -> {

            String nickName = userRepository.transformUserIdToNickname(comment.getUserId());

            CommentWithNickname commentWithNickname =  new CommentWithNickname();
            commentWithNickname.setNickname(nickName);
            commentWithNickname.setComment(comment);

            return commentWithNickname;
        }).toList();

        return commentListWithNickname;
    }

}
