package ru.aston.services.impl;

import lombok.RequiredArgsConstructor;
import ru.aston.converters.TaskConverter;
import ru.aston.dto.TaskDto;
import ru.aston.entity.Task;
import ru.aston.entity.User;
import ru.aston.repositories.TaskRepository;
import ru.aston.services.TaskService;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskConverter taskConverter;

    @Override
    public List<TaskDto> getAllTasks() throws SQLException {
        return taskRepository.getAllTasks().stream()
                .map(taskConverter::entityToDto)
                .toList();
    }

    @Override
    public List<TaskDto> getTasksByExecutor(User executor) throws SQLException {
        return taskRepository.getExecutorTasks(executor).stream()
                .map(taskConverter::entityToDto)
                .toList();
    }

    @Override
    public void updateTask(Long id, String title, String status, String description, String time_end) throws SQLException {
        taskRepository.updateTask(id, title, status, description, time_end);
    }

    @Override
    public void removeTask(Long id) throws SQLException {
        taskRepository.removeTask(id);
    }

    @Override
    public void addTask(Task task) throws SQLException {
        taskRepository.addTask(task);
    }
}
