package ru.aston.services;

import ru.aston.dto.TaskDto;
import ru.aston.entity.Task;
import ru.aston.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface TaskService {
    List<TaskDto> getAllTasks() throws SQLException;
    List<TaskDto> getTasksByExecutor(User executor) throws SQLException;
    void updateTask(Long id, String title, String status, String description, String time_end) throws SQLException;
    void removeTask(Long id) throws SQLException;
    void addTask(Task task) throws SQLException;
}
