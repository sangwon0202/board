package com.example.board.Controller;

import com.example.board.service.BoardListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("main")
public class MainController {

    private final BoardListService boardMetaService;

    @GetMapping()
    public String main(Model model) {
        model.addAttribute("boardList", boardMetaService.getBoardListAboutAll());
        return "main";
    }
}
