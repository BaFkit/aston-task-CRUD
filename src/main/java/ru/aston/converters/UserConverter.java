package ru.aston.converters;

import ru.aston.dto.UserDto;
import ru.aston.entity.User;

public class UserConverter {

    public UserDto entityToDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

}
