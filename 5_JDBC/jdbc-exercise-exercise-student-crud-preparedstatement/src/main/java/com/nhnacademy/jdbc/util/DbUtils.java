package com.nhnacademy.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {
    public DbUtils(){
        throw new IllegalStateException("Utility class");
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            //todo connection.
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/nhn_academy_0","root","P@ssw0rd");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

}