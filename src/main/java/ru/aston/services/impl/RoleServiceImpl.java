package ru.aston.services.impl;

import lombok.RequiredArgsConstructor;
import ru.aston.converters.RoleConverter;
import ru.aston.dto.RoleDto;
import ru.aston.repositories.RoleRepository;
import ru.aston.services.RoleService;

import java.util.List;

@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleConverter roleConverter;

    @Override
    public List<RoleDto> getRolesByUserId(Long userId) {
        return roleRepository.findRolesByUserId(userId).stream().map(roleConverter::entityToDto).toList();
    }

}
