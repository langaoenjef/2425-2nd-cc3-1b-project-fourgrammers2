package main.java.com.pennypal.view;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;
<<<<<<< HEAD

=======
>>>>>>> 028261e2002c4527a6dcfe97d80b3cc405ead52f
import main.java.com.pennypal.viewmodel.HomeViewModel;
import main.java.com.pennypal.model.Expense;

public class HomeExpenseView {
    private JPanel panel;
    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "PH"));
<<<<<<< HEAD
    private JLabel nameLabel, dateLabel, timeLabel, amountLabel, categoryLabel, descriptionLabel;

    public HomeExpenseView(CardLayout cardLayout, JPanel contentPanel, HomeViewModel viewModel,
                          Color primaryColor, Color accentColor, Color textColor, Color darkTextColor,
                          Color backgroundColor, Color cardColor, Color dividerColor, Color primaryDark) {
=======
    private JLabel nameLabel, categoryLabel, dateLabel, timeLabel, amountLabel, descriptionLabel;

    public HomeExpenseView(CardLayout cardLayout, JPanel contentPanel, HomeViewModel viewModel,
                           Color primaryColor, Color accentColor, Color textColor, Color darkTextColor,
                           Color backgroundColor, Color cardColor, Color dividerColor, Color primaryDark) {
>>>>>>> 028261e2002c4527a6dcfe97d80b3cc405ead52f

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(backgroundColor);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        nameLabel = new JLabel();
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        nameLabel.setForeground(darkTextColor);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

<<<<<<< HEAD
=======
        categoryLabel = new JLabel();
        categoryLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        categoryLabel.setForeground(darkTextColor);
        categoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

>>>>>>> 028261e2002c4527a6dcfe97d80b3cc405ead52f
        dateLabel = new JLabel();
        dateLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        dateLabel.setForeground(darkTextColor);
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

<<<<<<< HEAD
        timeLabel = new JLabel();
=======
        timeLabel = new JLabel(); // NEW TIME LABEL
>>>>>>> 028261e2002c4527a6dcfe97d80b3cc405ead52f
        timeLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        timeLabel.setForeground(darkTextColor);
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        amountLabel = new JLabel();
        amountLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
<<<<<<< HEAD
        amountLabel.setForeground(new Color(244, 67, 54)); // red for expenses
        amountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        categoryLabel = new JLabel();
        categoryLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        categoryLabel.setForeground(darkTextColor);
        categoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

=======
        amountLabel.setForeground(new Color(244, 67, 54));
        amountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

>>>>>>> 028261e2002c4527a6dcfe97d80b3cc405ead52f
        descriptionLabel = new JLabel();
        descriptionLabel.setFont(new Font("SansSerif", Font.ITALIC, 16));
        descriptionLabel.setForeground(darkTextColor);
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionLabel.setMaximumSize(new Dimension(300, 100));
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);

<<<<<<< HEAD
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(new Color(244, 67, 54));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteButton.addActionListener(e -> {
            viewModel.deleteSelectedExpense();
            cardLayout.show(contentPanel, "Home");
        });

=======
>>>>>>> 028261e2002c4527a6dcfe97d80b3cc405ead52f
        JButton backButton = new JButton("← Back");
        backButton.setBackground(primaryDark);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> cardLayout.show(contentPanel, "Home"));

        panel.add(nameLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
<<<<<<< HEAD
        panel.add(dateLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(timeLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(amountLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(categoryLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(descriptionLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(deleteButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(backButton);
    }

=======
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
>>>>>>> 028261e2002c4527a6dcfe97d80b3cc405ead52f
    public void update(HomeViewModel viewModel) {
        Expense expense = viewModel.getSelectedExpense();
        if (expense != null) {
            nameLabel.setText(expense.getName());
<<<<<<< HEAD
            dateLabel.setText("Date: " + expense.getDate());
            timeLabel.setText("Time: " + (expense.getTime() == null || expense.getTime().isEmpty() ? "N/A" : expense.getTime()));
            amountLabel.setText("Amount: " + currencyFormat.format(expense.getAmount()));
            categoryLabel.setText("Category: " + (expense.getCategory().isEmpty() ? "None" : expense.getCategory()));
            descriptionLabel.setText("<html><div style='text-align: center;'>Description: " +
                    (expense.getDescription().isEmpty() ? "None" : expense.getDescription()) + "</div></html>");
=======
            categoryLabel.setText("Category: " + expense.getCategory());
            dateLabel.setText("Date: " + expense.getDate());
            timeLabel.setText("Time: " + (expense.getTime() == null || expense.getTime().isEmpty() ? "N/A" : expense.getTime()));
            amountLabel.setText("Amount: " + currencyFormat.format(expense.getAmount()));
            descriptionLabel.setText("<html><div style='text-align: center;'>Description: " + 
                                     (expense.getDescription().isEmpty() ? "None" : expense.getDescription()) + "</div></html>");
>>>>>>> 028261e2002c4527a6dcfe97d80b3cc405ead52f
        }
    }

    public JPanel getPanel() {
        return panel;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 028261e2002c4527a6dcfe97d80b3cc405ead52f
