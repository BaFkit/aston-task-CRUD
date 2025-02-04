package ru.aston.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ru.aston.converters.UserConverter;
import ru.aston.dto.UserDto;
import ru.aston.entity.User;
import ru.aston.exceptions.AuthenticationException;
import ru.aston.exceptions.ResourceNotFoundException;
import ru.aston.repositories.UserRepository;
import ru.aston.services.UserService;
import ru.aston.utils.PasswordEncoder;
import ru.aston.validators.UserValidator;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final UserValidator userValidator;

    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAllUser().stream().map(userConverter::entityToDto).toList();
    }

    @Override
    public UserDto findUserById(Long id) {
        User user = userRepository.findUserById(id);
        if (user == null) {
            throw new ResourceNotFoundException("User with id " + id + " not found");
        } else {
            return userConverter.entityToDto(user);
        }
    }

    @Override
    public void addNewUser(String userName, String password, String email) {
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
    public void updateUserById(Long id, String userName, String password, String email) {
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
    public void deleteUserById(Long id) {
        userRepository.deleteUser(id);
    }

    @Override
    public Long authentication(String userName, String password) {
        User user = userRepository.findUserByUsername(userName);
        if (user == null) {
            throw new ResourceNotFoundException("User with name " + userName + " not found");
        } else {
            try {
                if (!PasswordEncoder.hashWithSHA256(password).equals(user.getPassword())) {
                    throw new AuthenticationException("Wrong password");
                }
            } catch (Exception e) {
                log.error(e);
                throw new RuntimeException(e);
            }
            return user.getId();
        }
    }
}
