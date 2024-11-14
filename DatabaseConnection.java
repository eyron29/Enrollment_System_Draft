package enrollmentDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//JDBC class: Java Database Connectivity to MySQL Server

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/enrollment_demo";
    private static final String USER = "root";
    private static final String PASSWORD = "admin123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}//class DatabaseConnection