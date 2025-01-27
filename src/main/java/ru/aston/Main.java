package ru.aston;

import lombok.extern.log4j.Log4j2;
import ru.aston.converters.UserConverter;
import ru.aston.repositories.UserRepository;
import ru.aston.services.impl.UserServiceImpl;

import java.sql.SQLException;

@Log4j2
public class Main {

    /**
     * Временное решение для проверки вывода
     * В дальнейшем будет реализован отдельный класс для работы с консолью
     */

    public static void main(String[] args) {
        UserServiceImpl userServiceImpl = new UserServiceImpl(new UserRepository(), new UserConverter());
        try {
            System.out.println(userServiceImpl.findAllUsers());
        } catch (SQLException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }
}
