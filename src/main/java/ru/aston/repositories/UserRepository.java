package ru.aston.repositories;

import ru.aston.entity.User;
import ru.aston.utils.UtilsDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public User findUserById(Long id) throws SQLException {
        String query = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = UtilsDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getLong("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email"));
            }
        }
        return null;
    }

    public List<User> findAllUser() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Connection conn = UtilsDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email")));
            }
        }
        return users;
    }

    public void saveUser (User user) throws SQLException {
        String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        try (Connection conn = UtilsDB.getConnection();
        PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.executeUpdate();
        }
    }

    public void deleteUser (Long id) throws SQLException {
        String query = "DELETE FROM users WHERE id = ?";
        try (Connection conn = UtilsDB.getConnection();
        PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }
}
