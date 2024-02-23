package com.example.board.DTO;


import lombok.Data;

import java.util.Date;

@Data
public class BoardUploadDto {
    private String userId;
    private String title;
    private String content;

}
