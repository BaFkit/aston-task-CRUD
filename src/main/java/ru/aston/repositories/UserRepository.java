package ru.aston.repositories;

import ru.aston.entity.User;
import ru.aston.utils.UtilsDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public List<User> findAllUser() throws SQLException {
        List<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = UtilsDB.getConnection().prepareStatement("SELECT * FROM users");
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            users.add(new User(rs.getLong("id"), rs.getString("username"), rs.getString("password"), rs.getString("email")));
        }
        return users;
    }

}
