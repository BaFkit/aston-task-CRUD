package ru.aston.services;

import ru.aston.dto.UserDto;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    List<UserDto> findAllUsers() throws SQLException;
    UserDto findUserById(Long id) throws SQLException;
    void addNewUser(String userName, String password, String email) throws SQLException;
    void updateUserById(Long id, String userName, String password, String email) throws SQLException;
    void deleteUserById(Long id) throws SQLException;
}
