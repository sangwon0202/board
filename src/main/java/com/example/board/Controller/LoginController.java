package com.example.board.Controller;

import com.example.board.DTO.LoginInfoDto;
import com.example.board.service.LoginValidationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginValidationService loginValidationService;

    @GetMapping()
    public String loginForm(HttpServletRequest request) {
        HttpSession session = request.getSession();
        // 이미 로그인 중인 경우
        if(session.getAttribute("loginInfo") !=  null) {
            return "redirect:/board";
        }
        else return "login";
    }

    @PostMapping()
    public String loginAction(@RequestParam String userId, @RequestParam String password,
                              HttpServletRequest request, Model model) {
        LoginInfoDto loginInfoDto = loginValidationService.loginValidation(userId, password);
        // 로그인 성공
        if(loginInfoDto != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loginInfo", loginInfoDto);
            return "redirect:/board";
        }
        // 로그인 실패 (아이디 비밀번호 안맞음)
        else {
            model.addAttribute("message","아이디 비밀번호가 맞지 않습니다.");
            model.addAttribute("url","/login");
            return "alert";
        }

    }

    @GetMapping("/logout")
    public String logoutAction(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("loginInfo") !=  null) {
            session.removeAttribute("loginInfo");
        }
        return "redirect:/board";
    }
}
