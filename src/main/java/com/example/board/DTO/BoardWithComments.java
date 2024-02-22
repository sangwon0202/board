package com.example.board.DTO;

import lombok.Data;

import java.util.List;

@Data
public class BoardWithComments {
    private Board board;
    private List<Comment> comments;

}
