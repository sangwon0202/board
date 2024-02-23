package com.example.board.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class BoardMetaDto {

    private String title;
    private String Nickname;
    private int commentCount;
    private Date uploadDate;
    private int boardId;

}
