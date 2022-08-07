package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        int i = 0;
        userService.saveUser("name1", "lastName1", (byte) 1);
        System.out.println("User с именем " + userService.getAllUsers().get(i++).getName() + " добавлен в базу данных");
        userService.saveUser("name2 ", "lastName2", (byte) 2);
        System.out.println("User с именем " + userService.getAllUsers().get(i++).getName() + " добавлен в базу данных");
        userService.saveUser("name3", "lastName3", (byte) 3);
        System.out.println("User с именем " + userService.getAllUsers().get(i++).getName() + " добавлен в базу данных");
        userService.saveUser("name4", "lastName4", (byte) 4);
        System.out.println("User с именем " + userService.getAllUsers().get(i).getName() + " добавлен в базу данных");

        System.out.println(userService.getAllUsers().toString());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
