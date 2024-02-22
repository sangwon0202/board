package com.example.board.Controller;

import com.example.board.DTO.Board;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.CommentRepository;
import com.example.board.repository.UserRepository;
import com.example.board.service.CommentListWithNicknameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("board")
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
}
