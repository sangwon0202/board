package com.example.board.DTO;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommentViewDto {
    private String nickname;
    private String content ;
    private Date uploadDate;

}
