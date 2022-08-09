package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.Driver;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String url = "jdbc:mysql://localhost:3306/pp_schema";
    private static final String username = "admin";
    private static final String password = "admin";

    private static Connection connection = buildConnection();

    public static Connection buildConnection() {
        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.print("Connection ERROR");
        }
        return connection;
    }
    public static Connection getConnection() {
        try {
            if (connection.isClosed()) {
                connection = buildConnection();
            }
        } catch (SQLException ignored) { }
        return connection;
    }
    public static void closeConnection(){
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ignored) { }
    }
}
