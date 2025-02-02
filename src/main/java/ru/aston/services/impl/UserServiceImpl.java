package ru.aston.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ru.aston.converters.UserConverter;
import ru.aston.dto.UserDto;
import ru.aston.entity.User;
import ru.aston.exceptions.ResourceNotFoundException;
import ru.aston.repositories.UserRepository;
import ru.aston.services.UserService;
import ru.aston.utils.PasswordEncoder;
import ru.aston.validators.UserValidator;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final UserValidator userValidator;

    @Override
    public List<UserDto> findAllUsers() throws SQLException {
        return userRepository.findAllUser().stream().map(userConverter::entityToDto).toList();
    }

    @Override
    public UserDto findUserById(Long id) throws SQLException {
        User user = userRepository.findUserById(id);
        if (user == null) {
            throw new ResourceNotFoundException("User with id " + id + " not found");
        }else {
            return userConverter.entityToDto(user);
        }
    }

    @Override
    public void addNewUser(String userName, String password, String email) throws SQLException {
        User user = new User();
        user.setUsername(userName);
        user.setEmail(email);
        try {
            user.setPassword(PasswordEncoder.hashWithSHA256(password));
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException(e);
        }
        userValidator.validate(user);
        userRepository.saveUser(user);
    }

    @Override
    public void updateUserById(Long id, String userName, String password, String email) throws SQLException {
        User user = userRepository.findUserById(id);
        user.setUsername(userName);
        user.setEmail(email);
        try {
            user.setPassword(PasswordEncoder.hashWithSHA256(password));
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException(e);
        }
        userRepository.saveUser(user);
    }

    @Override
    public void deleteUserById(Long id) throws SQLException {
        userRepository.deleteUser(id);
    }
}
