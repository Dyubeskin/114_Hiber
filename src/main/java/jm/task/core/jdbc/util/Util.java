package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static String dbURL = "jdbc:mysql://localhost:3306/mybd";
    private static String dbUsername ="root";
    private static String dbPassword = "root";
    private Connection con;
    private static SessionFactory sessionFactory;


    public static Connection getConnection(){

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbURL,dbUsername,dbPassword);
            if(!connection.isClosed()){
                System.out.println("Соединение установлено");
            } else {
                System.out.println("Соединение с БД закрыто!");
            }
        } catch (SQLException throwables) {
            System.err.println("Не удалось загрузить класс драйвера");
        }
        return connection;
    }
    public Connection getCon() {
        return con;
    }

    public static SessionFactory getSessionFactory(){
        Properties properties = new Properties();
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty(Environment.HBM2DDL_AUTO, "update");
        properties.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        properties.setProperty(Environment.USER, "root");
        properties.setProperty(Environment.PASS, "root");
        properties.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/hibernate");
        Configuration cfg = new Configuration();
        cfg.setProperties(properties);
        cfg.addAnnotatedClass(User.class);
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(cfg.getProperties()).build();
        sessionFactory = cfg.buildSessionFactory(serviceRegistry);
        return sessionFactory;

    }
    // реализуйте настройку соеденения с БД
}
