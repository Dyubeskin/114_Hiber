package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Util util = new Util();
    private  PreparedStatement preparedStatement;
    private ResultSet rs;
    private Connection connection = Util.getConnection();
    private Statement statement;



    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String MY_TABLE = "CREATE TABLE IF NOT EXISTS Users ("
                + "id INT(64) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                + "name VARCHAR(45),"
                + "lastName VARCHAR(45),"
                + "age INT(64))";
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(MY_TABLE);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
}

        /*
        try {
            statement = connection.createStatement();
            statement.executeUpdate(myTableName);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
*/
    public void dropUsersTable() {
    String  DELETE_TABLE = "DROP TABLE users";
        try( PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TABLE )){
            preparedStatement.executeUpdate(DELETE_TABLE);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String insertUser = "INSERT INTO Users (name, lastName, age) VALUES(?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(insertUser);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

/*
        String INSERT_USER = "INSERT INTO Users (name, lastName, age) VALUES(?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER )){
            rs = preparedStatement.executeQuery();
            preparedStatement.setString(1, name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
*/

    }

    public void removeUserById(long id) {

        String removeUser = "DELETE FROM Users WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(removeUser);
            preparedStatement.setLong(1, id);

            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        /*
        String  DELETE_USER = "DELETE FROM students WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER )) {
             rs = preparedStatement.executeQuery();

            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
*/
    }

    public List<User> getAllUsers() {
        String QUERY = "SELECT * FROM users";
        List<User> list = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            rs =  preparedStatement.executeQuery();
            while (rs.next()){
                User user = new User();
                user.setId((long) (rs.getInt("id")));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge((byte) rs.getInt("age"));
                list.add(user);
            };
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        String CLEAR_TABLE = "DELETE FROM users";
        try(PreparedStatement preparedStatement = connection.prepareStatement(CLEAR_TABLE )) {
            preparedStatement.executeUpdate(CLEAR_TABLE);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
