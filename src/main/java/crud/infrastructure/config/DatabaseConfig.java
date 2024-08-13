package crud.infrastructure.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//----para usar en casa---
public class DatabaseConfig {
private static final String URL = "jdbc:mysql://localhost:3306/crud";
    private static final String USER = "root";
    private static final String PASSWORD = "sebastrin77";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}


//---para usar en campus--
/*
public class DatabaseConfig {
    private static final String URL = "jdbc:mysql://localhost:3306/crud";
        private static final String USER = "campus2023";
        private static final String PASSWORD = "campus2023";
    
        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
}
*/