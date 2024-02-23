package com.example.board.Controller;

import com.example.board.DTO.BoardUploadDto;
import com.example.board.DTO.BoardViewDto;
import com.example.board.entity.Board;
import com.example.board.service.BoardUploadService;
import com.example.board.service.BoardViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardViewService boardViewService;
    private final BoardUploadService boardUploadService;

    @GetMapping()
    public String board(@RequestParam int boardId, Model model) {

        BoardViewDto boardViewDto = boardViewService.getBoardViewByBoardId(boardId);
        model.addAttribute("board", boardViewDto);
        return "board";
    }

    @GetMapping("/upload")
    public String uploadForm() {
        return "upload";
    }

    @PostMapping("/upload")
    public String upload (@RequestParam String userId,
                          @RequestParam String title,
                          @RequestParam String content) {
        BoardUploadDto boardUploadDto = new BoardUploadDto();
        boardUploadDto.setUserId(userId);
        boardUploadDto.setTitle(title);
        boardUploadDto.setContent(content);
        boardUploadService.boardUpload(boardUploadDto);
        return "redirect:/main";
    }


}
