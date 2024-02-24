package com.example.board.service;


import com.example.board.DTO.LoginInfoDto;
import com.example.board.entity.User;
import com.example.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginValidationService {

    private final UserRepository userRepository;

    public LoginInfoDto loginValidation(String userId, String password) {
        Optional<User> optionalUser = userRepository.getUserByUserId(userId);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            if(user.getPassword().equals(password)) {
                LoginInfoDto loginInfoDto = new LoginInfoDto();
                loginInfoDto.setUserId(user.getUserId());
                loginInfoDto.setNickname(user.getNickname());
                return loginInfoDto;
            }
        }
        return null;
    }

}
