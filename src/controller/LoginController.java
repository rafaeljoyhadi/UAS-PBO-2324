package controller;

import java.sql.*;

public class LoginController {
    public static boolean authenticateUser(String username, String password) {
        Connection connection = Connector.getInstance().getConnection();
        // if (connection == null) {
        // System.err.println("No database connection");
        // return false;
        // }
        String query = "SELECT * FROM users WHERE name = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }

        return false;
    }
}