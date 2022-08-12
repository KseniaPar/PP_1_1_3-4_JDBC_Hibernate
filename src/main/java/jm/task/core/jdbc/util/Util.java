package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.Driver;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String url = "jdbc:mysql://localhost:3306/pp_schema";
    private static final String username = "admin";
    private static final String password = "admin";
    /*
     * Hibernate Connection
     */
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();

                settings.put(Environment.URL, url);
                settings.put(Environment.USER, username);
                settings.put(Environment.PASS, password);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            }catch (Exception e) {
                System.out.println("Problem creating session factory");

            }


        }
        return sessionFactory;
    }

    /*
     * JDBC Connection
     */
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
