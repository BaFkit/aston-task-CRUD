package ru.aston.repositories;

import ru.aston.entity.Project;
import ru.aston.utils.UtilsDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepository {

    public Project findProjectById(Long id) throws SQLException {
        String query = "SELECT * FROM projects WHERE id = ?";
        try (Connection conn = UtilsDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Project(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getLong("owner_id"));
            }
        }
        return null;
    }

    public List<Project> findAllProjects() throws SQLException {
        List<Project> projects = new ArrayList<>();
        String query = "SELECT * FROM projects";
        try (Connection conn = UtilsDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                projects.add(new Project(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getLong("owner_id")));
            }
        }
        return projects;
    }

    public void saveProject(Project project) throws SQLException {
        String query = "INSERT INTO projects (name, description, start_date, end_date, owner_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = UtilsDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, project.getName());
            ps.setString(2, project.getDescription());
            ps.setDate(3, new java.sql.Date(project.getStartDate().getTime()));
            ps.setDate(4, new java.sql.Date(project.getEndDate().getTime()));
            ps.setLong(5, project.getOwnerId());

            ps.executeUpdate();
        }
    }
}