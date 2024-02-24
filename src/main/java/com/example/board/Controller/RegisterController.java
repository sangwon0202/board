package com.example.board.Controller;


import com.example.board.DTO.RegisterFormDto;
import com.example.board.DTO.RegisterResultDto;
import com.example.board.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisterController {

    private final RegisterService registerService;

    @GetMapping()
    public String registerForm() {
        return "register";
    }

    @PostMapping()
    public String registerAction(@RequestParam String userId,
                                 @RequestParam String password,
                                 @RequestParam String nickname,
                                 Model model) {

        if(userId.length() == 0) {
            model.addAttribute("message", "아이디를 입력해주세요.");
            model.addAttribute("url", "/register");
            return "alert";
        }
        if(password.length() == 0) {
            model.addAttribute("message", "비밀번호를 입력해주세요.");
            model.addAttribute("url", "/register");
            return "alert";
        }
        if(nickname.length() == 0) {
            model.addAttribute("message", "닉네임을 입력해주세요.");
            model.addAttribute("url", "/register");
            return "alert";
        }


        RegisterFormDto registerFormDto = new RegisterFormDto();
        registerFormDto.setUserId(userId);
        registerFormDto.setPassword(password);
        registerFormDto.setNickname(nickname);

        RegisterResultDto registerResultDto = registerService.register(registerFormDto);

        if(registerResultDto.getResult()) {
            model.addAttribute("message", "회원가입에 성공하였습니다!");
            model.addAttribute("url", "/");
        }
        else {
            model.addAttribute("message", registerResultDto.getMessage());
            model.addAttribute("url", "/register");
        }
        return "alert";
    }
}
