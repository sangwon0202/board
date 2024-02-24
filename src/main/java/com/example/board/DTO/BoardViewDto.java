package com.example.board.DTO;


import com.example.board.entity.Comment;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BoardViewDto {

    private int boardId;
    private String title;
    private String Nickname;
    private String content ;
    private Date uploadDate;
    private List<CommentViewDto> commentList;

}
