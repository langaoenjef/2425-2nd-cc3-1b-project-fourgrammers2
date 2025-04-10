package main.java.com.pennypal.view;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
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
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Add Income", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(darkTextColor);
        panel.add(title, gbc);

        JTextField nameField = new JTextField();
        nameField.setBorder(BorderFactory.createTitledBorder("Name"));
        nameField.setBackground(cardColor);
        nameField.setForeground(darkTextColor);
        panel.add(nameField, gbc);

        JTextField amountField = new JTextField();
        amountField.setBorder(BorderFactory.createTitledBorder("Amount"));
        amountField.setBackground(cardColor);
        amountField.setForeground(darkTextColor);
        panel.add(amountField, gbc);

        // Date dropdowns
        LocalDate today = LocalDate.now();
        Integer[] days = new Integer[31];
        for (int i = 1; i <= 31; i++) days[i - 1] = i;
        JComboBox<Integer> dayDropdown = new JComboBox<>(days);
        dayDropdown.setSelectedItem(today.getDayOfMonth());

        Integer[] months = new Integer[12];
        for (int i = 1; i <= 12; i++) months[i - 1] = i;
        JComboBox<Integer> monthDropdown = new JComboBox<>(months);
        monthDropdown.setSelectedItem(today.getMonthValue());

        Integer[] years = new Integer[5];
        int currentYear = today.getYear();
        for (int i = 0; i < 5; i++) years[i] = currentYear - 2 + i;
        JComboBox<Integer> yearDropdown = new JComboBox<>(years);
        yearDropdown.setSelectedItem(currentYear);

        JPanel datePanel = new JPanel();
        datePanel.setBackground(backgroundColor);
        datePanel.setBorder(BorderFactory.createTitledBorder("Date (DD/MM/YYYY)"));
        datePanel.add(dayDropdown);
        datePanel.add(monthDropdown);
        datePanel.add(yearDropdown);
        panel.add(datePanel, gbc);

        JTextField timeField = new JTextField(LocalTime.now().toString().substring(0, 5));
        timeField.setBorder(BorderFactory.createTitledBorder("Time (HH:mm)"));
        timeField.setBackground(cardColor);
        timeField.setForeground(darkTextColor);
        panel.add(timeField, gbc);

        JTextArea descriptionArea = new JTextArea(3, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setBorder(BorderFactory.createTitledBorder("Description"));
        descriptionArea.setBackground(cardColor);
        descriptionArea.setForeground(darkTextColor);
        panel.add(descriptionArea, gbc);

        JButton submitButton = new JButton("Add Income");
        submitButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        submitButton.setBackground(primaryColor);
        submitButton.setForeground(textColor);
        submitButton.setFocusPainted(false);
        submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submitButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                double amount = Double.parseDouble(amountField.getText());

                int day = (Integer) dayDropdown.getSelectedItem();
                int month = (Integer) monthDropdown.getSelectedItem();
                int year = (Integer) yearDropdown.getSelectedItem();
                String date = String.format("%04d-%02d-%02d", year, month, day);

                String time = timeField.getText();
                String description = descriptionArea.getText();

                viewModel.addIncome(new main.java.com.pennypal.model.Income(name, amount, date, time, description));
                cardLayout.show(contentPanel, "Home");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Please enter a valid numeric amount.");
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
