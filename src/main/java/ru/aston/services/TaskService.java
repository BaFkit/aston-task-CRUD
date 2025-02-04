package ru.aston.services;

import ru.aston.dto.TaskDto;

import java.sql.SQLException;
import java.util.List;

public interface TaskService {
    List<TaskDto> getAllTasks() throws SQLException;
    List<TaskDto> getTasksByExecutorID(Long userID) throws SQLException;
    void updateTask(Long id, String title, String status, String description, String time_end) throws SQLException;
    void removeTask(Long id) throws SQLException;
    void addTask(String title, String status, String description, String time_end,
                 Long executor_id, Long author_id, Long project_id) throws SQLException;
}
