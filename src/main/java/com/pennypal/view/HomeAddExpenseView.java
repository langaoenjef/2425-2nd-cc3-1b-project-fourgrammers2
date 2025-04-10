package main.java.com.pennypal.view;

import javax.swing.*;
import java.awt.*;
import main.java.com.pennypal.viewmodel.HomeViewModel;

public class HomeAddExpenseView {
    private JPanel panel;

    public HomeAddExpenseView(
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

        JLabel title = new JLabel("Add Expense", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(darkTextColor);
        panel.add(title, gbc);

        JTextField nameField = new JTextField();
        nameField.setBorder(BorderFactory.createTitledBorder("Category"));
        nameField.setBackground(cardColor);
        nameField.setForeground(darkTextColor);
        panel.add(nameField, gbc);

        JTextField amountField = new JTextField();
        amountField.setBorder(BorderFactory.createTitledBorder("Amount"));
        amountField.setBackground(cardColor);
        amountField.setForeground(darkTextColor);
        panel.add(amountField, gbc);

        JButton submitButton = new JButton("Add Expense");
        submitButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        submitButton.setBackground(new Color(244, 67, 54)); // Accent red for expenses
        submitButton.setForeground(textColor);
        submitButton.setFocusPainted(false);
        submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submitButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                String name = nameField.getText();
                viewModel.addExpense(name, amount, java.time.LocalDate.now().toString());
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
