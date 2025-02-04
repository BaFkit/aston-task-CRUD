package ru.aston.repositories;

import ru.aston.entity.Task;
import ru.aston.utils.UtilsDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    //поиск задачи по id исполнителя
    public List<Task> getExecutorTasks(Long userID) throws SQLException {
        List<Task> taskList = new ArrayList<>();
        try (Connection conn = UtilsDB.getConnection()) {
            String sqlQuery = "Select * FROM tasks WHERE executor_id = ?";
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setLong(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                taskList.add(new Task(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("status"),
                        rs.getString("description"),
                        rs.getString("time_end"),
                        rs.getLong("executor_id"),
                        rs.getLong("author_id"),
                        rs.getLong("project_id")
                ));
            }
            return taskList;

        }

    }

    public List<Task> getAllTasks() throws SQLException {
        // Метод для получения списка всех задач из таблицы в виде List<Task>
        try (Connection conn = UtilsDB.getConnection()) {
            String sqlQuery = "SELECT * FROM tasks";
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ResultSet rs = ps.executeQuery();
            ArrayList<Task> taskList = new ArrayList<>();
            while (rs.next()) {
                taskList.add(new Task(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("status"),
                        rs.getString("description"),
                        rs.getString("time_end"),
                        rs.getLong("executor_id"),
                        rs.getLong("author_id"),
                        rs.getLong("project_id")
                ));
            }
            return taskList;
        }

    }

    public void updateTask(Long id, String title, String status, String description, String time_end)
            throws SQLException {
        //Обновляет все параметры у задачи, по ее id
        try (Connection conn = UtilsDB.getConnection()) {
            String sqlQuery = "UPDATE tasks SET " +
                    "title = ?, " +
                    "status = ?, " +
                    "description = ?, " +
                    "time_end = ? " +
                    "WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, title);
            ps.setString(2, status);
            ps.setString(3, description);
            ps.setString(4, time_end);
            ps.setLong(5, id);
            ps.executeUpdate();
        }
    }

    public void removeTask(Long id) throws SQLException {
        // Удаляет запись по нужному id
        try (Connection conn = UtilsDB.getConnection()) {
            String sqlQuery = "DELETE FROM tasks " +
                    "WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    public void addTask(String title, String status, String description, String time_end,
                        Long executor_id, Long author_id, Long project_id) throws SQLException {
        // Добавляет новую задачу
        try (Connection conn = UtilsDB.getConnection()) {
            String sqlQuery = "INSERT INTO tasks(title,status,description,time_end,executor_id,author_id,project_id) " +
                    "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1, title);
            ps.setString(2, status);
            ps.setString(3, description);
            ps.setString(4, time_end);
            ps.setLong(5, executor_id);
            ps.setLong(6, author_id);
            ps.setLong(7, project_id);
            ps.executeUpdate();
        }
    }

}
