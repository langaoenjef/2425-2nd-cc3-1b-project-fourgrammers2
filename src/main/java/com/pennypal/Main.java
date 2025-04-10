package main.java.com.pennypal;

import main.java.com.pennypal.viewmodel.HomeViewModel;
import main.java.com.pennypal.view.*;
import main.java.com.pennypal.storage.StorageManager;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        HomeViewModel homeViewModel = new HomeViewModel();
        StorageManager.load(homeViewModel);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            StorageManager.save(homeViewModel);
        }));

        // Colors
        Color primaryColor = new Color(33, 150, 243);
        Color accentColor = new Color(255, 193, 7);
        Color textColor = Color.WHITE;
        Color darkTextColor = new Color(33, 33, 33);
        Color backgroundColor = new Color(250, 250, 250);
        Color cardColor = Color.WHITE;
        Color dividerColor = new Color(224, 224, 224);
        Color primaryDark = new Color(25, 118, 210);
        Color primaryLight = new Color(144, 202, 249);

        // Layout manager
        CardLayout cardLayout = new CardLayout();
        JPanel contentPanel = new JPanel(cardLayout);

        // Views
        HomeView homeView = new HomeView(cardLayout, contentPanel, homeViewModel,
                primaryColor, accentColor, textColor, darkTextColor,
                backgroundColor, cardColor, dividerColor, primaryDark);

        HomeAddTransactionView addTransactionView = new HomeAddTransactionView(
                cardLayout, contentPanel, homeViewModel,
                primaryColor, primaryDark, primaryLight,
                accentColor, textColor, darkTextColor,
                backgroundColor, cardColor
        );

        HomeAddIncomeView addIncomeView = new HomeAddIncomeView(
                cardLayout, contentPanel, homeViewModel,
                primaryColor, accentColor, textColor, darkTextColor,
                backgroundColor, cardColor, dividerColor, primaryDark);

        HomeAddExpenseView addExpenseView = new HomeAddExpenseView(
                cardLayout, contentPanel, homeViewModel,
                primaryColor, accentColor, textColor, darkTextColor,
                backgroundColor, cardColor, dividerColor, primaryDark);

        HomeSetLimitView setLimitView = new HomeSetLimitView(
                cardLayout, contentPanel, homeViewModel,
                primaryColor, accentColor, textColor, darkTextColor,
                backgroundColor, cardColor, dividerColor, primaryDark);

        HomeExpenseView expenseView = new HomeExpenseView(
                cardLayout, contentPanel, homeViewModel,
                primaryColor, accentColor, textColor, darkTextColor,
                backgroundColor, cardColor, dividerColor, primaryDark);

                homeView.setExpenseClickListener(expense -> {
                    homeViewModel.setSelectedExpense(expense);
                    expenseView.update(homeViewModel); // ✅ Pass the view model
                    cardLayout.show(contentPanel, "ExpenseDetails");
                });
                

        // Add views to card layout
        contentPanel.add(homeView.getPanel(), "Home");
        contentPanel.add(addTransactionView.getPanel(), "AddOptions");
        contentPanel.add(addIncomeView.getPanel(), "AddIncome");
        contentPanel.add(addExpenseView.getPanel(), "AddExpense");
        contentPanel.add(setLimitView.getPanel(), "SetLimit");
        contentPanel.add(expenseView.getPanel(), "ExpenseDetails");

        // Main window
        JFrame frame = new JFrame("PennyPal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(contentPanel);
        frame.setVisible(true);
    }
}
