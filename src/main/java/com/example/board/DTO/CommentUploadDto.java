package com.example.board.DTO;


import lombok.Data;

@Data
public class CommentUploadDto {

    private int boardId;
    private String userId;
    private String content;

}