package controller;

import java.sql.*;

public class Connector {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/uaspbo2324?user=root&password=";
    private static Connector instance;

    // * SINGLETON PATTERN FOR GETTING THE DATABASE CONNECTION
    private Connector() {

    }

    public static Connector getInstance() {
        if (instance == null) {
            synchronized (Connector.class) {
                if (instance == null) {
                    instance = new Connector();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
        return connection;
    }
}

// public static void main(String[] args) {
// Connector connector = Connector.getInstance();
// Connection connection = connector.getConnection();
// if (connection != null) {
// System.out.println("Connection successful!");
// } else {
// System.out.println("Connection failed!");
// }
// }
