package ru.aston;

import ru.aston.converters.TaskConverter;
import ru.aston.converters.UserConverter;
import ru.aston.dto.RoleDto;
import ru.aston.dto.TaskDto;
import ru.aston.dto.UserDto;
import ru.aston.repositories.TaskRepository;
import ru.aston.repositories.UserRepository;
import ru.aston.services.impl.TaskServiceImpl;
import ru.aston.services.impl.UserServiceImpl;
import ru.aston.validators.UserValidator;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    Long userID;
    List<RoleDto> userRoles;

    public Menu(Long id, List<RoleDto> userRoles) throws SQLException {
        this.userID = id;
        this.userRoles = userRoles;
        System.out.println("Чтобы перейти в нужный пункт, напишите цифру.(1,2,3 и т.д.)");
        System.out.println("Меню выбора: ");
        switch (this.userRoles.getFirst().getRoleName()) {
            case "admin":
                admin();
            case "executor":
                executor();
            case "manager":
                projectManager();
        }
    }

    private void admin(){
        System.out.println("1. Посмотреть users");
        System.out.println("2. Удалить users");
        System.out.println("3. Выйти из системы");
        Scanner scannerMenu = new Scanner(System.in);
        int choice = scannerMenu.nextInt();
        UserServiceImpl usi = new UserServiceImpl(new UserRepository(),
                new UserConverter(), new UserValidator());
        switch (choice){
            case 1:
                List<UserDto> listTasks = usi.findAllUsers();
                for (UserDto ud : listTasks){
                    System.out.println("Название: " + ud.getUsername() + "\nEmail: " + ud.getEmail());
                }
                System.out.println("Список пользователей выведен! Пока...");
                break;

            case 2:
                System.out.print("Введите username для удаления: ");
                Scanner scannerUsername = new Scanner(System.in);
                Long id = usi.getUserIdByUsername(scannerUsername.nextLine());
                usi.deleteUserById(id);
                System.out.println("User " + usi.findUserById(id).getUsername() +" удален! Пока...");
                break;

            case 3:
                break;
        }
    }

    private void projectManager() throws SQLException{
        System.out.println("1. Добавить task");
        System.out.println("2. Удалить task");
        System.out.println("3. Выйти из системы");
        Scanner scannerMenu = new Scanner(System.in);
        int choice = scannerMenu.nextInt();
        TaskServiceImpl tsi = new TaskServiceImpl(new TaskRepository(), new TaskConverter());
        switch (choice){

            case 1:
                System.out.print("Заголовок: ");
                Scanner scannerTitle = new Scanner(System.in);
                System.out.print("Статус: ");
                Scanner scannerStatus = new Scanner(System.in);
                System.out.print("Содержание: ");
                Scanner scannerDescription = new Scanner(System.in);
                System.out.print("Срок выполнения: ");
                Scanner scannerTimeEnd = new Scanner(System.in);
                System.out.print("ID исполнителя: ");
                Scanner scannerExecutorID = new Scanner(System.in);
                System.out.print("ID проекта: ");
                Scanner scanner = new Scanner(System.in);

                tsi.addTask(scannerTitle.nextLine(),scannerStatus.nextLine(),scannerDescription.nextLine(),
                        scannerTimeEnd.nextLine(),scannerExecutorID.nextLong(),this.userID, scanner.nextLong());

                System.out.println("Задача добавлена! Пока...");

            case 2:
                System.out.print("Введите ID task для удаления: ");
                Scanner scannerID = new Scanner(System.in);
                tsi.removeTask(scannerID.nextLong());
                System.out.println("Задача удалена! Пока...");

            case 3:
                System.out.println("Пока...");
                break;
        }
    }

    private void executor() throws SQLException {
        System.out.println("1. Посмотреть весь список tasks");
        System.out.println("2. Выйти из системы");
        Scanner scannerMenu = new Scanner(System.in);
        int choice = scannerMenu.nextInt();
        switch (choice){

            case 1:
                TaskServiceImpl tsi = new TaskServiceImpl(new TaskRepository(),new TaskConverter());
                List<TaskDto> listTasks = tsi.getTasksByExecutorID(this.userID);
                for (TaskDto td : listTasks){
                    System.out.println("Название: " + td.getTitle() + "\nОписание: " + td.getDescription());
                }
                System.out.println("Список задач выведен! Пока...");
                break;

            case 2:
                System.out.println("Пока...");
                break;
        }
    }
}
