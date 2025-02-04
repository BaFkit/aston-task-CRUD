package ru.aston.repositories;

import lombok.extern.log4j.Log4j2;
import ru.aston.entity.User;
import ru.aston.utils.UtilsDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class UserRepository {

    public User findUserByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = UtilsDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getLong("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email"));
            }
        } catch (SQLException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
        return null;
    }

    public User findUserById(Long id) {
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
        } catch (SQLException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<User> findAllUser() {
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
        } catch (SQLException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
        return users;
    }

    public void saveUser(User user) {
        String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        try (Connection conn = UtilsDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(Long id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (Connection conn = UtilsDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }
}
