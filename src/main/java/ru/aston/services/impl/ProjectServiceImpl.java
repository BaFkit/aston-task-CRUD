package ru.aston.services.impl;

import lombok.RequiredArgsConstructor;
import ru.aston.converters.ProjectConverter;
import ru.aston.dto.ProjectDto;
import ru.aston.entity.Project;
import ru.aston.repositories.ProjectRepository;
import ru.aston.services.ProjectService;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectConverter projectConverter;

    @Override
    public List<ProjectDto> findAllProjects() throws SQLException {
        return projectRepository.findAllProjects().stream().map(projectConverter::entityToDto).toList();
    }

    @Override
    public ProjectDto findProjectById(Long id) throws SQLException {
        Project project = projectRepository.findProjectById(id);
        return projectConverter.entityToDto(project);
    }
}