package com.example.board.service;

import com.example.board.DTO.RegisterFormDto;
import com.example.board.DTO.RegisterResultDto;
import com.example.board.entity.User;
import com.example.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;

    public RegisterResultDto register(RegisterFormDto registerFormDto) {

        RegisterResultDto registerResultDto = new RegisterResultDto();

        // 기존 아이디와 중복될 경우
        if(userRepository.getUserByUserId(registerFormDto.getUserId()).isPresent()) {
            registerResultDto.setResult(false);
            registerResultDto.setMessage("아이디가 중복되었습니다.");
            return registerResultDto;
        }

        User user = new User();
        user.setUserId(registerFormDto.getUserId());
        user.setNickname(registerFormDto.getNickname());
        user.setPassword(registerFormDto.getPassword());

        userRepository.insertUser(user);
        registerResultDto.setResult(true);

        return registerResultDto;
    }

}
