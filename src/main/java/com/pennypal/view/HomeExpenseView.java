package main.java.com.pennypal.view;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;
import main.java.com.pennypal.viewmodel.HomeViewModel;
import main.java.com.pennypal.model.Expense;

public class HomeExpenseView {
    private JPanel panel;
    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "PH"));
    private JLabel nameLabel, categoryLabel, dateLabel, timeLabel, amountLabel, descriptionLabel;

    public HomeExpenseView(CardLayout cardLayout, JPanel contentPanel, HomeViewModel viewModel,
                           Color primaryColor, Color accentColor, Color textColor, Color darkTextColor,
                           Color backgroundColor, Color cardColor, Color dividerColor, Color primaryDark) {

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(backgroundColor);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        nameLabel = new JLabel();
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        nameLabel.setForeground(darkTextColor);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        categoryLabel = new JLabel();
        categoryLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        categoryLabel.setForeground(darkTextColor);
        categoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        dateLabel = new JLabel();
        dateLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        dateLabel.setForeground(darkTextColor);
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        timeLabel = new JLabel(); // NEW TIME LABEL
        timeLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        timeLabel.setForeground(darkTextColor);
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        amountLabel = new JLabel();
        amountLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        amountLabel.setForeground(new Color(244, 67, 54));
        amountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        descriptionLabel = new JLabel();
        descriptionLabel.setFont(new Font("SansSerif", Font.ITALIC, 16));
        descriptionLabel.setForeground(darkTextColor);
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionLabel.setMaximumSize(new Dimension(300, 100));
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton backButton = new JButton("← Back");
        backButton.setBackground(primaryDark);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> cardLayout.show(contentPanel, "Home"));

        panel.add(nameLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(categoryLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(dateLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(timeLabel); // Show Time
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(amountLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(descriptionLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(backButton);
    }

    // Call this method before showing the screen to refresh the expense data
    public void update(HomeViewModel viewModel) {
        Expense expense = viewModel.getSelectedExpense();
        if (expense != null) {
            nameLabel.setText(expense.getName());
            categoryLabel.setText("Category: " + expense.getCategory());
            dateLabel.setText("Date: " + expense.getDate());
            timeLabel.setText("Time: " + (expense.getTime() == null || expense.getTime().isEmpty() ? "N/A" : expense.getTime()));
            amountLabel.setText("Amount: " + currencyFormat.format(expense.getAmount()));
            descriptionLabel.setText("<html><div style='text-align: center;'>Description: " + 
                                     (expense.getDescription().isEmpty() ? "None" : expense.getDescription()) + "</div></html>");
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
