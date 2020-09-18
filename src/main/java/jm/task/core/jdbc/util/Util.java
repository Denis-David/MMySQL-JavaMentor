package jm.task.core.jdbc.util;
import java.sql.*;

public class Util {
    private Connection connection;
    public Util() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb", "test2", "ei7veeChu4bo");
            if (connection != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

         */
    }
    public Connection getConnection() {
        return connection;
    }

}