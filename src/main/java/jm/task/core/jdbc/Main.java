package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.service.UserService;

import java.util.List;


public class Main {
    private final static UserService userService = new UserServiceImpl();
    public static void main(String[] args) {

        userService.createUsersTable();
        userService.saveUser("Antonio", "Banderas", (byte) 55);
        userService.saveUser("Irina", "Banderas", (byte) 28);
        userService.saveUser("Marianna", "Vetrova", (byte) 56);
        userService.saveUser("Peter", "Peterovich", (byte) 45);

        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();



    }
}
