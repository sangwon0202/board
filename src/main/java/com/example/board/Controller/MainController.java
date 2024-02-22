package com.example.board.Controller;

import com.example.board.DTO.Board;
import com.example.board.DTO.BoardInfo;
import com.example.board.repository.BoardRepository;
import com.example.board.service.BoardInfoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("main")
public class MainController {

    private final BoardInfoListService boardInfoListService;

    @GetMapping()
    public String main(Model model) {

        List<BoardInfo> boardInfoList = boardInfoListService.getBoardInfoListAboutAll();
        model.addAttribute("boardInfoList", boardInfoList);

        return "main";
    }
}
