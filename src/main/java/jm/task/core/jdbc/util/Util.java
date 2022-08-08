package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.Driver;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String url = "jdbc:mysql://localhost:3306/pp_schema";
    private static final String username = "admin";
    private static final String password = "admin";

    public static Connection getConnection(){
        Connection connection = null;
        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.print("Connection ERROR");
        }
        return connection;
    }
}
