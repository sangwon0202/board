package com.example.board.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class BoardInfo {
    private int boardId;
    private String nickname;
    private String title;
    private Date uploadDate;

    private int commentCount;
}
