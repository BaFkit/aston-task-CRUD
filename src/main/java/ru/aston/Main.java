package ru.aston;

import lombok.extern.log4j.Log4j2;
import ru.aston.converters.ProjectConverter;
import ru.aston.converters.UserConverter;
import ru.aston.repositories.ProjectRepository;
import ru.aston.repositories.UserRepository;
import ru.aston.services.impl.ProjectServiceImpl;
import ru.aston.services.impl.UserServiceImpl;
import ru.aston.validators.UserValidator;

import java.sql.SQLException;

@Log4j2
public class Main {

    /**
     * Временное решение для проверки вывода
     * В дальнейшем будет реализован отдельный класс для работы с консолью
     */

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl(new UserRepository(), new UserConverter(), new UserValidator());
        System.out.println(userService.findAllUsers());
        System.out.println(userService.findUserById(4L));

        ProjectServiceImpl projectService = new ProjectServiceImpl(new ProjectRepository(), new ProjectConverter());
        try {
            System.out.println(projectService.findAllProjects());
            System.out.println(projectService.findProjectById(1L));
        } catch (SQLException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }
}
