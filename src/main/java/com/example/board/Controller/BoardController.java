package com.example.board.Controller;

import com.example.board.DTO.BoardUploadDto;
import com.example.board.DTO.BoardViewDto;
import com.example.board.DTO.CommentUploadDto;
import com.example.board.DTO.LoginInfoDto;
import com.example.board.service.BoardListService;
import com.example.board.service.BoardUploadService;
import com.example.board.service.BoardViewService;
import com.example.board.service.CommentUploadService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardViewService boardViewService;
    private final BoardUploadService boardUploadService;

    private final BoardListService boardListService;
    private final CommentUploadService commentUploadService;


    @GetMapping()
    public String main(Model model) {
        model.addAttribute("boardList", boardListService.getBoardListAboutAll());
        return "board";
    }

    @GetMapping("/{boardId}")
    public String board(@PathVariable("boardId") int boardId, Model model) {
        BoardViewDto boardViewDto = boardViewService.getBoardViewByBoardId(boardId);
        model.addAttribute("board", boardViewDto);
        model.addAttribute("boardList", boardListService.getBoardListAboutAll());
        return "board";
    }

    @GetMapping("/upload")
    public String uploadForm(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        // 로그인이 되어 있을 경우
        if(session.getAttribute("loginInfo") != null) {
            return "upload";
        }
        // 로그인이 되어 있지 않을 경우
        else {
            model.addAttribute("message","게시글 작성 전 로그인을 해주세요.");
            model.addAttribute("url","/login");
            return "alert";
        }
    }

    @PostMapping("/upload")
    public String upload (HttpServletRequest request,
                          @RequestParam String title,
                          @RequestParam String content,
                          Model model) {

        HttpSession session = request.getSession();
        if(session.getAttribute("loginInfo") != null) {
            String userId = ((LoginInfoDto)session.getAttribute("loginInfo")).getUserId();
            BoardUploadDto boardUploadDto = new BoardUploadDto();
            boardUploadDto.setUserId(userId);
            boardUploadDto.setTitle(title);
            boardUploadDto.setContent(content);
            boardUploadService.boardUpload(boardUploadDto);
            return "redirect:/board";
        }
        else {
            model.addAttribute("message","로그인이 필요합니다");
            model.addAttribute("url","/login");
            return "alert";
        }
    }

    @PostMapping("/comment")
    public String comment (@RequestParam int boardId,
                          @RequestParam String content,
                           HttpServletRequest request,
                           RedirectAttributes redirectAttributes,
                           Model model
    ) {

        HttpSession session = request.getSession();
        if(session.getAttribute("loginInfo") != null) {
            CommentUploadDto commentUploadDto = new CommentUploadDto();
            String userId = ((LoginInfoDto)session.getAttribute("loginInfo")).getUserId();
            commentUploadDto.setUserId(userId);
            commentUploadDto.setBoardId(boardId);
            commentUploadDto.setContent(content);
            commentUploadService.CommentUpload(commentUploadDto);

            redirectAttributes.addAttribute("boardId", boardId);
            return "redirect:/board/{boardId}";
        }
        else {
            model.addAttribute("message","로그인이 필요합니다");
            model.addAttribute("url","/login");
            return "alert";
        }
    }

}
