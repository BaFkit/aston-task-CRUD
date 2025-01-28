package ru.aston.dto;

import lombok.*;

/**
 * Подготовлен Дто для отдачи на запросы.
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDto {

    private String username;
    private String email;

}
