package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(ConfigurationManager.getInstance().getDataBaseUrl(), ConfigurationManager.getInstance().getUsername(), ConfigurationManager.getInstance().getPassword());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
