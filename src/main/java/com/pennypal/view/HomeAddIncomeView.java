package main.java.com.pennypal.view;

import javax.swing.*;
import java.awt.*;
import main.java.com.pennypal.model.Income;
import main.java.com.pennypal.viewmodel.HomeViewModel;

public class HomeAddIncomeView {
    private JPanel panel;

    public HomeAddIncomeView(
        CardLayout cardLayout,
        JPanel contentPanel,
        HomeViewModel viewModel,
        Color primaryColor,
        Color accentColor,
        Color textColor,
        Color darkTextColor,
        Color backgroundColor,
        Color cardColor,
        Color dividerColor,
        Color primaryDark
    ) {
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(backgroundColor);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(15, 0, 15, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Add Income", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(darkTextColor);
        panel.add(title, gbc);

        JTextField amountField = new JTextField();
        amountField.setBorder(BorderFactory.createTitledBorder("Amount"));
        amountField.setBackground(cardColor);
        amountField.setForeground(darkTextColor);
        panel.add(amountField, gbc);

        JTextField dateField = new JTextField();
        dateField.setBorder(BorderFactory.createTitledBorder("Date (e.g. 2025-04-11)"));
        dateField.setBackground(cardColor);
        dateField.setForeground(darkTextColor);
        panel.add(dateField, gbc);

        JButton submitButton = new JButton("Add Income");
        submitButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        submitButton.setBackground(primaryColor);
        submitButton.setForeground(textColor);
        submitButton.setFocusPainted(false);
        submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submitButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                String date = dateField.getText();
                Income income = new Income(amount, date);
                viewModel.addIncome(income);
                cardLayout.show(contentPanel, "Home");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Please enter a valid amount.");
            }
        });
        panel.add(submitButton, gbc);

        JButton backButton = new JButton("← Back");
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        backButton.setBackground(cardColor);
        backButton.setForeground(darkTextColor);
        backButton.setFocusPainted(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> cardLayout.show(contentPanel, "AddOptions"));
        panel.add(backButton, gbc);
    }

    public JPanel getPanel() {
        return panel;
    }
}
