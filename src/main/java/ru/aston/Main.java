package ru.aston;

import lombok.extern.log4j.Log4j2;
import ru.aston.converters.RoleConverter;
import ru.aston.converters.UserConverter;
import ru.aston.dto.RoleDto;
import ru.aston.repositories.RoleRepository;
import ru.aston.repositories.UserRepository;
import ru.aston.services.impl.RoleServiceImpl;
import ru.aston.services.impl.UserServiceImpl;
import ru.aston.validators.UserValidator;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

@Log4j2
public class Main {

    /**
     * Временное решение для проверки вывода
     * В дальнейшем будет реализован отдельный класс для работы с консолью
     */

    public static void main(String[] args) throws SQLException {
        UserServiceImpl usi = new UserServiceImpl(new UserRepository(),new UserConverter(), new UserValidator());
        System.out.print("Введите логин: ");
        Scanner scannerLogin = new Scanner(System.in);
        String username = scannerLogin.nextLine();

        System.out.print("Введите пароль: ");
        Scanner scannerPassword = new Scanner(System.in);
        String password = scannerPassword.nextLine();
        Long userId = usi.authentication(username, password);
        RoleServiceImpl rsi = new RoleServiceImpl(new RoleRepository(), new RoleConverter());
        List<RoleDto> listUserRoles = rsi.getRolesByUserId(userId);
        new Menu(userId, listUserRoles);
    }
}
