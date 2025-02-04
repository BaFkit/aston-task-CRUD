package ru.aston.services;

import ru.aston.dto.RoleDto;

import java.util.List;

public interface RoleService {

    List<RoleDto> getRolesByUserId(Long userId);

}
