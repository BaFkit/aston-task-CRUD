package ru.aston.utils;

import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Log4j2
public class UtilsDB {

    private static final String dbURL = "jdbc:h2:mem:aston_task_CRUD;INIT=RUNSCRIPT FROM 'classpath:db/init.sql'";
    private static final String username = "sa";
    private static final String Password = "";

    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(dbURL, username, Password);
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return connection;
    }

}
