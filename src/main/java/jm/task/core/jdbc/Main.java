package jm.task.core.jdbc;

import jm.task.core.jdbc.service.*;



import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("Andrew", "Saymayname", (byte) 27);
        service.saveUser("July", "Borna", (byte) 23);
        service.saveUser("Guly", "Ovzy", (byte) 32);
        service.saveUser("Sers", "Larnov", (byte) 28);
        System.out.println(service.getAllUsers());
        service.cleanUsersTable();
        service.dropUsersTable();

    }
}
