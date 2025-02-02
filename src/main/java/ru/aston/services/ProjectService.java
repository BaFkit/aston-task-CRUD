package ru.aston.services;

import ru.aston.dto.ProjectDto;

import java.sql.SQLException;
import java.util.List;

public interface ProjectService {
    List<ProjectDto> findAllProjects() throws SQLException;
    ProjectDto findProjectById(Long id) throws SQLException;
}