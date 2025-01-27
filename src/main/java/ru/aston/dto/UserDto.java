package ru.aston.dto;

import lombok.*;

/**
 * Подготовлен Дто для отдачи на запросы.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String username;
    private String email;

}
