package com.example.board.Controller;

import com.example.board.DTO.Board;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.UserRepository;
import com.example.board.service.CommentListWithNicknameService;
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

    private final BoardRepository boardRepository;
    private final CommentListWithNicknameService commentListWithNicknameService;
    private final UserRepository userRepository;

    @GetMapping()
    public String board(@RequestParam int boardId, Model model) {

        Optional<Board> optionalBoard = boardRepository.getBoardByBoardId(boardId);
        if(!optionalBoard.isPresent()) {
            model.addAttribute("errorMessage", "존재하지 않는 게시글입니다.");
        }
        Board board = optionalBoard.get();
        model.addAttribute("board", board);
        model.addAttribute("commentListWithNickname", commentListWithNicknameService.getCommentListWithNickname(boardId));
        model.addAttribute("nickname", userRepository.transformUserIdToNickname(board.getUserId()));

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
        Board board = new Board();
        board.setUserId(userId);
        board.setTitle(title);
        board.setContent(content);
        board.setUploadDate(new Date());

        board = boardRepository.insertBoard(board);
        if(board.getBoardId() == 0) return "error";
        return "redirect:/main";
    }


}
