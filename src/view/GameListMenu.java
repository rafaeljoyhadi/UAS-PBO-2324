package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.Games;
import controller.GameListController;
import controller.TransactionController;

public class GameListMenu {
    private JFrame frame;
    private JButton transactionsButton;
    private JPanel gamePanel;
    private GameListController gamelistController;
    private TransactionController transactionController;
    private String username;

    public GameListMenu(String username) {
        this.username = username;
        frame = new JFrame("Game List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 200);
        frame.setLocationRelativeTo(null);

        transactionsButton = new JButton("Transactions");
        transactionsButton.addActionListener(e -> openTransactionsMenu());
        gamePanel = new JPanel(new GridLayout(0, 2));

        frame.add(transactionsButton, BorderLayout.NORTH);
        frame.add(gamePanel, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.dispose();
            new LoginPage();
        });
        frame.add(backButton, BorderLayout.SOUTH);

        gamelistController = new GameListController();
        transactionController = new TransactionController();
        setGames(gamelistController.getAllGames());
    }

    public void setGames(List<Games> games) {
        for (Games game : games) {
            JPanel panel = new JPanel();
            panel.add(new JLabel(game.getName()));
            panel.add(new JLabel(game.getGenre()));
            panel.add(new JLabel(String.valueOf(game.getPrice())));
            JButton buyButton = new JButton("Buy Game");
            buyButton.addActionListener(e -> {
                boolean success = transactionController.buyGame(username, game.getId());
                if (success) {
                    JOptionPane.showMessageDialog(frame, "Purchase Successful", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Purchase Failed", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            panel.add(buyButton);
            gamePanel.add(panel);
        }
    }

    public void openTransactionsMenu() {
        new TransactionsMenu(username);
    }

    public void show() {
        frame.setVisible(true);
    }
}