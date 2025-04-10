package main.java.com.pennypal.view;

import javax.swing.*;
import java.awt.*;
import main.java.com.pennypal.viewmodel.HomeViewModel;

public class HomeAddTransactionView {
    private JPanel panel;

    public HomeAddTransactionView(CardLayout cardLayout, JPanel contentPanel, HomeViewModel homeViewModel,
                               Color primaryColor, Color primaryDark, Color primaryLight,
                               Color accentColor, Color textColor, Color darkTextColor,
                               Color backgroundColor, Color cardColor)
    {
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(backgroundColor);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(15, 0, 15, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Add Transaction", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(darkTextColor);
        panel.add(title, gbc);

        JButton incomeButton = createButton("Add Income", primaryColor, textColor, () -> {
            cardLayout.show(contentPanel, "AddIncome");
        });
        panel.add(incomeButton, gbc);

        JButton expenseButton = createButton("Add Expense", primaryColor, textColor, () -> {
            cardLayout.show(contentPanel, "AddExpense");
        });
        panel.add(expenseButton, gbc);

        JButton limitButton = createButton("Set Limit", accentColor, darkTextColor, () -> {
            cardLayout.show(contentPanel, "SetLimit");
        });
        panel.add(limitButton, gbc);

        JButton backButton = new JButton("← Back");
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        backButton.setBackground(cardColor);
        backButton.setForeground(darkTextColor);
        backButton.setFocusPainted(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> cardLayout.show(contentPanel, "Home"));
        panel.add(backButton, gbc);
    }

    private JButton createButton(String text, Color bgColor, Color fgColor, Runnable onClick) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 18));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addActionListener(e -> onClick.run());
        return button;
    }

    public JPanel getPanel() {
        return panel;
    }
}
