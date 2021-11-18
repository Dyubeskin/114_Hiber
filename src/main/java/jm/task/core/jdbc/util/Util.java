package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static String dbURL = "jdbc:mysql://localhost:3306/mybd";
    private static String dbUsername ="root";
    private static String dbPassword = "root";
    private Connection con;


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
    // реализуйте настройку соеденения с БД
}
