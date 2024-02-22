package com.example.board.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class CommentWithNickname {
    private Comment comment;
    private String nickname;
}
