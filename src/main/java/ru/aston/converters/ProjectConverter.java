package ru.aston.converters;

import ru.aston.dto.ProjectDto;
import ru.aston.entity.Project;

public class ProjectConverter {
    public ProjectDto entityToDto(Project project) {
        return ProjectDto.builder()
                .name(project.getName())
                .description(project.getDescription())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .build();
    }
}
