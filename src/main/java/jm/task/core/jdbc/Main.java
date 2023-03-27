package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.service.UserService;


public class Main {
    private final static UserService userService = new UserServiceImpl();
    public static void main(String[] args) {

        userService.createUsersTable();
        userService.saveUser("Sergey", "Salamatin", (byte) 33);
        userService.saveUser("Peter", "Viborsky", (byte) 82);
        userService.saveUser("Irina", "Shakirova", (byte) 28);
        userService.saveUser("Marianna", "Vetrova", (byte) 56);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
