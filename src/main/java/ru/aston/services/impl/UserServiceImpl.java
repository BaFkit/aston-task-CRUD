package ru.aston.services.impl;

import lombok.RequiredArgsConstructor;
import ru.aston.converters.UserConverter;
import ru.aston.dto.UserDto;
import ru.aston.repositories.UserRepository;
import ru.aston.services.UserService;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Override
    public List<UserDto> findAllUsers() throws SQLException {
        return userRepository.findAllUser().stream().map(userConverter::entityToDto).toList();
    }
}
