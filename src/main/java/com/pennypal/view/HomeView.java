package main.java.com.pennypal.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class HomeView {
    private JPanel panel;
    private JLabel balanceLabel;
    private double balance;
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private Color primaryColor, accentColor, textColor, darkTextColor, backgroundColor, cardColor, dividerColor, primaryDark;

    public HomeView(CardLayout cardLayout, JPanel contentPanel, double balance, Color primaryColor, Color accentColor,
                    Color textColor, Color darkTextColor, Color backgroundColor, Color cardColor, Color dividerColor, Color primaryDark) {
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        this.balance = balance;
        this.primaryColor = primaryColor;
        this.accentColor = accentColor;
        this.textColor = textColor;
        this.darkTextColor = darkTextColor;
        this.backgroundColor = backgroundColor;
        this.cardColor = cardColor;
        this.dividerColor = dividerColor;
        this.primaryDark = primaryDark;

        this.panel = createHomePanel();
    }

    public JPanel getPanel() {
        return panel;
    }

    private JPanel createHomePanel() {
        JPanel home = new JPanel(new BorderLayout());
        home.setBackground(backgroundColor);

        // Balance display
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(primaryColor);
        topPanel.setBorder(new EmptyBorder(20, 0, 30, 0));

        JLabel balanceTitle = new JLabel("CURRENT BALANCE", SwingConstants.CENTER);
        balanceTitle.setFont(new Font("SansSerif", Font.BOLD, 14));
        balanceTitle.setForeground(new Color(255, 255, 255, 220));

        balanceLabel = new JLabel(String.format("$%.2f", balance), SwingConstants.CENTER);
        balanceLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        balanceLabel.setForeground(textColor);

        topPanel.add(balanceTitle, BorderLayout.NORTH);
        topPanel.add(balanceLabel, BorderLayout.CENTER);
        home.add(topPanel, BorderLayout.NORTH);

        // Transactions
        JPanel transactionsPanel = new JPanel();
        transactionsPanel.setLayout(new BoxLayout(transactionsPanel, BoxLayout.Y_AXIS));
        transactionsPanel.setBackground(cardColor);
        transactionsPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, dividerColor),
            new EmptyBorder(10, 15, 10, 15)
        ));

        JLabel recentLabel = new JLabel("RECENT TRANSACTIONS", SwingConstants.LEFT);
        recentLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        recentLabel.setForeground(darkTextColor);
        recentLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        transactionsPanel.add(recentLabel);
        transactionsPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        transactionsPanel.add(createTransactionItem("Food", "May 15", -12.50));
        transactionsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        transactionsPanel.add(createTransactionItem("Salary", "May 1", 1200.00));
        transactionsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        transactionsPanel.add(createTransactionItem("Transport", "May 10", -25.00));

        JScrollPane scrollPane = new JScrollPane(transactionsPanel);
        scrollPane.setBorder(null);
        home.add(scrollPane, BorderLayout.CENTER);

        // Add transaction button
        JButton addButton = new JButton("+");
        addButton.setFont(new Font("SansSerif", Font.BOLD, 30));
        addButton.setForeground(textColor);
        addButton.setBackground(accentColor);
        addButton.setFocusPainted(false);
        addButton.setBorder(BorderFactory.createEmptyBorder());
        addButton.setPreferredSize(new Dimension(70, 70));
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addButton.setBorderPainted(false);
        addButton.setOpaque(true);
        addButton.setContentAreaFilled(true);
        addButton.addActionListener(e -> cardLayout.show(contentPanel, "AddOptions"));

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(70, 70));
        layeredPane.setLayout(null);
        addButton.setBounds(0, 0, 70, 70);
        layeredPane.add(addButton, JLayeredPane.POPUP_LAYER);

        JPanel buttonContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonContainer.setBackground(new Color(0, 0, 0, 0));
        buttonContainer.add(layeredPane);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(0, 0, 0, 0));
        bottomPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
        bottomPanel.add(buttonContainer, BorderLayout.CENTER);

        home.add(bottomPanel, BorderLayout.SOUTH);

        return home;
    }

    private JPanel createTransactionItem(String category, String date, double amount) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(cardColor);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, dividerColor),
            new EmptyBorder(10, 0, 10, 0)
        ));

        JLabel categoryLabel = new JLabel(category);
        categoryLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        categoryLabel.setForeground(darkTextColor);

        JLabel dateLabel = new JLabel(date);
        dateLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        dateLabel.setForeground(new Color(0, 0, 0, 150));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(cardColor);
        leftPanel.add(categoryLabel);
        leftPanel.add(dateLabel);

        JLabel amountLabel = new JLabel(String.format("$%.2f", amount));
        amountLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        amountLabel.setForeground(amount < 0 ? new Color(244, 67, 54) : new Color(76, 175, 80));

        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(amountLabel, BorderLayout.EAST);

        return panel;
    }
}
