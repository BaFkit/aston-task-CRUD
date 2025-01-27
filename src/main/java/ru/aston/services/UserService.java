package ru.aston.services;

import ru.aston.dto.UserDto;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    List<UserDto> findAllUsers() throws SQLException;
}
