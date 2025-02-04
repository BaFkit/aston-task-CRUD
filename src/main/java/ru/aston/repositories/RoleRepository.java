package ru.aston.repositories;

import lombok.extern.log4j.Log4j2;
import ru.aston.entity.Role;
import ru.aston.utils.UtilsDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class RoleRepository {

    public List<Role> findRolesByUserId(Long userId) {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT * FROM roles WHERE user_id = ?";
        try (Connection conn = UtilsDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, userId);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                roles.add(new Role(resultSet.getLong("role_id"), resultSet.getString("role_name")));
            }
        } catch (SQLException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
        return roles;

    }
}
