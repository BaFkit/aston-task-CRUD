package ru.aston.dto;


import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProjectDto {
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
}
