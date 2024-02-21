package com.example.board.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class Board {

    int boardId;
    String userId;
    String title;
    String content;
    Date uploadDate;

}
