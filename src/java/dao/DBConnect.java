package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnect {
    private final String url = "jdbc:mysql://localhost:3306/shopping";
    private final String user = "root";
    private final String password = "";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    public Connection getConnection() throws Exception {
        Class.forName(JDBC_DRIVER);
        return  DriverManager.getConnection(url, user, password);
    }

    public static void main(String[] args) {
        try {
            System.out.println("Database connect succesfull");
            System.out.println(new DBConnect().getConnection() + ",");
        }catch(Exception e) {
            System.out.println(e);
        }
    }

}
