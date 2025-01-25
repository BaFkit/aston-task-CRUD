package ru.aston;

import ru.aston.services.UserService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();

        try {
            System.out.println(userService.findAllUsers());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
