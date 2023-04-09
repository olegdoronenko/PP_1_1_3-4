package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.service.UserService;


public class Main {
    private final static UserService userService = new UserServiceImpl();
    public static void main(String[] args) {

        userService.dropUsersTable();

        userService.createUsersTable();
        userService.saveUser("Sergey", "Salamatin", (byte) 33);
        userService.saveUser("Peter", "Viborsky", (byte) 82);
        userService.dropUsersTable();
        userService.createUsersTable();

        userService.saveUser("Irina", "Shakirova", (byte) 28);
        //userService.removeUserById(1);
        userService.getAllUsers();
        //userService.cleanUsersTable();
        //userService.dropUsersTable();


    }
}
