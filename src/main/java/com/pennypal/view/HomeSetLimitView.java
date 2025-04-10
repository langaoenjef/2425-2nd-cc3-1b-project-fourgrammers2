package main.java.com.pennypal.view;

import javax.swing.*;
import java.awt.*;
import main.java.com.pennypal.viewmodel.HomeViewModel;

public class HomeSetLimitView {
    private JPanel panel;

    public HomeSetLimitView(CardLayout cardLayout, JPanel contentPanel, HomeViewModel viewModel,
                             Color primaryColor, Color accentColor, Color textColor, Color darkTextColor,
                             Color backgroundColor, Color cardColor, Color dividerColor, Color primaryDark) {

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(backgroundColor);
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JLabel title = new JLabel("Set Spending Limit");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(darkTextColor);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField limitField = new JTextField();
        limitField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        limitField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        limitField.setBorder(BorderFactory.createTitledBorder("Limit Amount (₱)"));

        String[] options = {"Daily", "Weekly", "Monthly"};
        JComboBox<String> typeCombo = new JComboBox<>(options);
        typeCombo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        typeCombo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        typeCombo.setBorder(BorderFactory.createTitledBorder("Limit Type"));

        JButton saveBtn = new JButton("Save");
        saveBtn.setBackground(accentColor);
        saveBtn.setForeground(textColor);
        saveBtn.setFocusPainted(false);
        saveBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
        saveBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        saveBtn.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(limitField.getText());
                String type = (String) typeCombo.getSelectedItem();
                viewModel.setSpendingLimit(amount, type);  // ✅ Hook to ViewModel
                cardLayout.show(contentPanel, "Home");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Please enter a valid number.");
            }
        });

        JButton backBtn = new JButton("← Back");
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        backBtn.setBackground(primaryDark);
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        backBtn.addActionListener(e -> cardLayout.show(contentPanel, "AddOptions"));

        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(limitField);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(typeCombo);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(saveBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(backBtn);
    }

    public JPanel getPanel() {
        return panel;
    }
}
