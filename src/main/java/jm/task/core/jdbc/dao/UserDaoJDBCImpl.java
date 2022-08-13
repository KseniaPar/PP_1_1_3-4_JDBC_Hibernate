package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        Connection connection = Util.getConnection();
        try (connection; Statement s = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "firstName VARCHAR(45) NOT NULL, lastName VARCHAR(45) NOT NULL, age TINYINT NOT NULL)";
            connection.setAutoCommit(false);
            s.executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignored) { }
            System.out.println("Could not create table");
        }
    }

    public void dropUsersTable() {

        Connection connection = Util.getConnection();
        try (connection; Statement s = connection.createStatement()) {
            connection.setAutoCommit(false);
            s.executeUpdate("DROP TABLE IF EXISTS users");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignored) { }
            System.out.println("Could not delete table");
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO users (firstName, lastName, age) VALUES (?, ?, ?)";
        Connection connection = Util.getConnection();
        try (connection; PreparedStatement s = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            s.setString(1, name);
            s.setString(2, lastName);
            s.setByte(3, age);
            s.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignored) { }
            System.out.println("Could not save user");
        }
    }

    public void removeUserById(long id) {

        String sql = "DELETE FROM users WHERE id = ?";
        Connection connection = Util.getConnection();
        try (connection; PreparedStatement s = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            s.setLong(1, id);
            s.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignored) { }
            System.out.println("Could not delete user");
        }
    }

    public List<User> getAllUsers() {

        List<User> list = new ArrayList<>();
        try (Connection connection = Util.getConnection(); Statement s = connection.createStatement()) {
            ResultSet resultSet = s.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge((byte) resultSet.getInt(4));
                list.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Could not get all user");
        }
        return list;
    }

    public void cleanUsersTable() {

        Connection connection = Util.getConnection();
        try (connection; Statement s = connection.createStatement()) {
            connection.setAutoCommit(false);
            s.executeUpdate("TRUNCATE users");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignored) { }
            System.out.println("Could not clear table");
        }
    }
}
