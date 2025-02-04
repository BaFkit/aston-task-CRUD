package ru.aston.services;

import ru.aston.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAllUsers();
    UserDto findUserById(Long id);
    void addNewUser(String userName, String password, String email);
    void updateUserById(Long id, String userName, String password, String email);
    void deleteUserById(Long id);
    Long getUserIdByUsername(String username);
    Long authentication(String userName, String password);
}
