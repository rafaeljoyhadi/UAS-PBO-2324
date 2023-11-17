package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.Transactions;
import controller.TransactionController;

public class TransactionsMenu {
    private JFrame frame;
    private JTable transactionsTable;
    private TransactionController transactionController;

    public TransactionsMenu(String username) {
        frame = new JFrame("Transactions");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 200);
        frame.setLocationRelativeTo(null);

        transactionController = new TransactionController();
        List<Transactions> transactions = transactionController.getTransactionsForUser(username);

        String[] columnNames = { "User ID", "User Name", "Game ID", "Game Name", "Total Price" };
        Object[][] data = new Object[transactions.size()][5];
        for (int i = 0; i < transactions.size(); i++) {
            Transactions transaction = transactions.get(i);
            data[i][0] = transaction.getUserId();
            data[i][1] = transaction.getUserName();
            data[i][2] = transaction.getGameId();
            data[i][3] = transaction.getGameName();
            data[i][4] = transaction.getTotalPrice();
        }

        transactionsTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(transactionsTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.dispose();
            new GameListMenu(username).show();
        });
        frame.add(backButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}