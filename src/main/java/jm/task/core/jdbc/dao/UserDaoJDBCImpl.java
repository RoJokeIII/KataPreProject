package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }
    private static Connection connection;
    private final String nameTable = "usertable";


    public void createConnection(){
        if(Util.isClose(connection)){
            connection = Util.getConnection();
        }
    }

    public void closeConnection(){
        if (!Util.isClose(connection)){
            Util.close(connection);
            connection = null;
        }
    }

    public void createUsersTable() {
        createConnection();
        try {
            ResultSet tables = connection.getMetaData().getTables(null, null, nameTable, null);
            if (tables.next()) {
                return;
            } else {
                Statement statement = connection.createStatement();
                String createTableSQL = "CREATE TABLE " + nameTable + " (" +
                        "id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
                        "name VARCHAR(255), " +
                        "lastName VARCHAR(255), " +
                        "age TINYINT" +
                        ")";
                statement.executeUpdate(createTableSQL);
                System.out.println(4);
            }
        } catch (SQLException e) {
            System.out.println("Не удалось создать таблицу");
            throw new RuntimeException(e);
        }
        closeConnection();
    }

    public void dropUsersTable() {
        createConnection();
        try {
            ResultSet tables = connection.getMetaData().getTables(null, null, nameTable, null);
            if (tables.next()) {
                Statement statement = connection.createStatement();
                String deleteTableSQL = "DROP TABLE " + nameTable;
                statement.executeUpdate(deleteTableSQL);
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Не удалось удалить таблицу");
            throw new RuntimeException(e);
        }
        closeConnection();
    }

    public void saveUser(String name, String lastName, byte age) {
        createConnection();
        try {
            ResultSet tables = connection.getMetaData().getTables(null, null, nameTable, null);
            if (tables.next()) {
                String saveUserTableSQL = "INSERT INTO " + nameTable + " (name, lastName, age) VALUES (?,?,?)";
                PreparedStatement statement = connection.prepareStatement(saveUserTableSQL);
                statement.setString(1, name);
                statement.setString(2, lastName);
                statement.setByte(3, age);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Не удалось сохранить пользователя");
            throw new RuntimeException(e);
        }
        closeConnection();
    }

    public void removeUserById(long id) {
        createConnection();
        try {
            ResultSet tables = connection.getMetaData().getTables(null, null, nameTable, null);
            if (tables.next()) {
                String removeUserTableSQL = "DELETE FROM " + nameTable + " WHERE id=?";
                PreparedStatement statement = connection.prepareStatement(removeUserTableSQL);
                statement.setLong(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Не удалось удалить пользователя");
            throw new RuntimeException(e);
        }
        closeConnection();
    }

    public List<User> getAllUsers() {
        createConnection();
        List<User> users = new ArrayList<>();
        try {
            ResultSet tables = connection.getMetaData().getTables(null, null, nameTable, null);
            if (tables.next()) {
                String getUsersSQL = "SELECT id, name, lastName, age FROM " + nameTable;
                Statement statement = connection.createStatement();
                ResultSet rez = statement.executeQuery(getUsersSQL);
                while (rez.next()) {
                    User user = new User();
                    user.setId(rez.getLong("id"));
                    user.setName(rez.getString("name"));
                    user.setLastName(rez.getString("lastName"));
                    user.setAge(rez.getByte("age"));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            System.out.println("Не удалось удалить пользователей");
            throw new RuntimeException(e);
        }
        closeConnection();
        return users;
    }

    public void cleanUsersTable() {
        createConnection();
        try {
            ResultSet tables = connection.getMetaData().getTables(null, null, nameTable, null);
            if (tables.next()) {
                String cleanTableSQL = "DELETE FROM " + nameTable;
                Statement statement = connection.createStatement();
                statement.executeUpdate(cleanTableSQL);
            }
        } catch (SQLException e) {
            System.out.println("Не удалось удалить пользователей");
            throw new RuntimeException(e);
        }
        closeConnection();
    }
}
