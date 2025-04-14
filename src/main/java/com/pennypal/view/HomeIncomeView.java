package main.java.com.pennypal.view;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;

import main.java.com.pennypal.viewmodel.HomeViewModel;
import main.java.com.pennypal.model.Income;

public class HomeIncomeView extends JPanel {
    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "PH"));
    private JLabel nameLabel, dateLabel, timeLabel, amountLabel, descriptionLabel;

    public HomeIncomeView(CardLayout cardLayout, JPanel contentPanel, HomeViewModel viewModel,
                          Color primaryColor, Color accentColor, Color textColor, Color darkTextColor,
                          Color backgroundColor, Color cardColor, Color dividerColor, Color primaryDark) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(backgroundColor);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        nameLabel = new JLabel();
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        nameLabel.setForeground(darkTextColor);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        dateLabel = new JLabel();
        dateLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        dateLabel.setForeground(darkTextColor);
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        timeLabel.setForeground(darkTextColor);
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        amountLabel = new JLabel();
        amountLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        amountLabel.setForeground(new Color(76, 175, 80)); // green for income
        amountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        descriptionLabel = new JLabel();
        descriptionLabel.setFont(new Font("SansSerif", Font.ITALIC, 16));
        descriptionLabel.setForeground(darkTextColor);
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionLabel.setMaximumSize(new Dimension(300, 100));
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(new Color(244, 67, 54));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteButton.addActionListener(e -> {
            viewModel.deleteSelectedIncome();
            cardLayout.show(contentPanel, "Home");
        });

        JButton backButton = new JButton("← Back");
        backButton.setBackground(primaryDark);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> cardLayout.show(contentPanel, "Home"));

        add(nameLabel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(dateLabel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(timeLabel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(amountLabel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(descriptionLabel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(deleteButton);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(backButton);
    }

    public void update(HomeViewModel viewModel) {
        Income income = viewModel.getSelectedIncome();
        if (income != null) {
            nameLabel.setText(income.getName());
            dateLabel.setText("Date: " + income.getDate());
            timeLabel.setText("Time: " + (income.getTime() == null || income.getTime().isEmpty() ? "N/A" : income.getTime()));
            amountLabel.setText("Amount: " + currencyFormat.format(income.getAmount()));
            descriptionLabel.setText("<html><div style='text-align: center;'>Description: " + 
                                     (income.getDescription().isEmpty() ? "None" : income.getDescription()) + "</div></html>");
        }
    }
}