package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private String hostDB = "127.0.0.1";
    private String portDB = "3306";
    private String nameDB = "user";
    private String userDB = "root";
    private String passwordDB = "Lemoncheg2";

    Connection connection;

    public Connection getConnection() {
        createConnection();
        return connection;
    }

    public void createConnection() {
        String urlDB = "jdbc:mysql://" + hostDB + ":" + portDB + "/" + nameDB;
        try {
            connection = DriverManager.getConnection(urlDB, userDB, passwordDB);
        } catch (SQLException e) {
            System.out.println("Не получилось подключиться к базе данных.");
            throw new RuntimeException(e);
        }
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Не удалось закрыть подключение");
            throw new RuntimeException(e);
        }
    }

}
