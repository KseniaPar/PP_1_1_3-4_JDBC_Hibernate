package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.HashSet;
import java.util.Set;


public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        Set<User> users= new HashSet<>();
        users.add(new User("Serkan", "Bolad", (byte) 35));
        users.add(new User("Eda", "Yildiz", (byte) 28));
        users.add(new User("Piril", "Baytekin", (byte) 41));
        users.add(new User("Engin", "Sezgin", (byte) 30));

        for (User user : users) {
            userService.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.println("User с именем " + user.getName() + " добавлен в базу данных.");
        }

        System.out.println("\nСписок всех User в баззе:");
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
