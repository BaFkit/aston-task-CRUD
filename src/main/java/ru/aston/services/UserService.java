package ru.aston.services;

import ru.aston.dto.UserDto;
import ru.aston.repositories.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepository();
    }

    public List<UserDto> findAllUsers() throws SQLException {
        return userRepository.findAllUser().stream().map(user -> UserDto.builder().username(user.getUsername())
                .email(user.getEmail())
                .build()).toList();
    }
}
