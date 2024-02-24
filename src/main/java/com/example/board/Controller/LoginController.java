package com.example.board.Controller;

import com.example.board.DTO.LoginInfoDto;
import com.example.board.service.LoginValidationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
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
    String loginForm(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("loginInfo") !=  null) {
            return "redirect:/board";
        }
        else return "login";
    }

    @PostMapping()
    String loginAction(@RequestParam String userId, @RequestParam String password,
                       HttpServletRequest request) {
        LoginInfoDto loginInfoDto = loginValidationService.loginValidation(userId, password);
        if(loginInfoDto != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loginInfo", loginInfoDto);
            return "redirect:/board";
        }
        else {
            return "redirect:/login";
        }

    }

    @GetMapping("/logout")
    String logoutAction(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("loginInfo") !=  null) {
            session.removeAttribute("loginInfo");
        }
        return "redirect:/board";
    }
}
