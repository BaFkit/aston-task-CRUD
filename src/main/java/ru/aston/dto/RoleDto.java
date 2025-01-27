package ru.aston.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Подготовлен Дто для отдачи на запросы.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    private String roleName;

}
