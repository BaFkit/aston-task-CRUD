package ru.aston.converters;

import ru.aston.dto.RoleDto;
import ru.aston.entity.Role;

public class RoleConverter {

    public RoleDto entityToDto(Role role) {
        return RoleDto.builder()
                .roleName(role.getName())
                .build();
    }

}
