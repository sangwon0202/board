package com.example.board.repository;

import com.example.board.DTO.Board;
import com.example.board.DTO.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> getUserByUserId(String userId);
    User insertUser(User user);
}
