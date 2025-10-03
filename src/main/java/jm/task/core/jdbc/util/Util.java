package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private static String hostDB = "127.0.0.1";
    private static String portDB = "3306";
    private static String nameDB = "user";
    private static String userDB = "root";
    private static String passwordDB = "Lemoncheg2";

    public static Connection getConnection() {
        String urlDB = "jdbc:mysql://" + hostDB + ":" + portDB + "/" + nameDB;
        try {
            return DriverManager.getConnection(urlDB, userDB, passwordDB);
        } catch (SQLException e) {
            System.out.println("Не получилось подключиться к базе данных.");
            throw new RuntimeException(e);
        }
    }
    public static boolean isClose(Connection connection){
        try {
            if(connection == null){
                return true;
            }
            return connection.isClosed();
        } catch (SQLException e) {
            System.out.println("Не удалось проверить подключение");
            throw new RuntimeException(e);
        }
    }
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
