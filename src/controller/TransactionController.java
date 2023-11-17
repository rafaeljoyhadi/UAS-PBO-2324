package controller;

import model.Transactions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionController {
    private Connection connection;

    public TransactionController() {
        this.connection = Connector.getInstance().getConnection();
    }

    // * BUY GAME
    public boolean buyGame(String username, int gameId) {
        String getUserIdQuery = "SELECT id FROM users WHERE name = ?";
        int userId;
        try {
            PreparedStatement getUserIdStmt = connection.prepareStatement(getUserIdQuery);
            getUserIdStmt.setString(1, username);
            ResultSet rs = getUserIdStmt.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("id");
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        String insertQuery = "INSERT INTO transactions (user_id, game_id) VALUES (?, ?)";
        try {
            PreparedStatement insertStmt = connection.prepareStatement(insertQuery);
            insertStmt.setInt(1, userId);
            insertStmt.setInt(2, gameId);
            int result = insertStmt.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // * VIEW TRANSACTIONS
    public List<Transactions> getTransactionsForUser(String username) {
        List<Transactions> transactions = new ArrayList<>();
        String query = "SELECT t.id, t.user_id, u.name, t.game_id, g.name, g.price " +
                "FROM transactions t " +
                "JOIN users u ON t.user_id = u.id " +
                "JOIN games g ON t.game_id = g.id " +
                "WHERE u.name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Transactions transaction = new Transactions(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getInt(6));
                transactions.add(transaction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }
}