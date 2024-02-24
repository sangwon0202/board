package com.example.board.service;


import com.example.board.DTO.CommentUploadDto;
import com.example.board.entity.Comment;
import com.example.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CommentUploadService {

    private final CommentRepository commentRepository;


    public void CommentUpload(CommentUploadDto commentUploadDto) {

        Comment comment = new Comment();
        comment.setUploadDate(new Date());
        comment.setContent(commentUploadDto.getContent());
        comment.setUserId(commentUploadDto.getUserId());
        comment.setBoardId(commentUploadDto.getBoardId());

        commentRepository.insertComment(comment);
    }

}
