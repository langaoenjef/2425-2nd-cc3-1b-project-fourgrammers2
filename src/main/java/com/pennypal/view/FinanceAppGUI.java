package main.java.com.pennypal.view;

import main.java.com.pennypal.viewmodel.HomeViewModel;
import main.java.com.pennypal.storage.StorageManager;
import main.java.com.pennypal.view.HomeIncomeView;

import javax.swing.*;
import java.awt.*;

public class FinanceAppGUI {
    private JFrame frame;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private HomeView homeView;

    public FinanceAppGUI() {
        frame = new JFrame("PennyPal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Colors from original Main
        Color primaryColor = new Color(33, 150, 243);
        Color accentColor = new Color(255, 193, 7);
        Color textColor = Color.WHITE;
        Color darkTextColor = new Color(33, 33, 33);
        Color backgroundColor = new Color(250, 250, 250);
        Color cardColor = Color.WHITE;
        Color dividerColor = new Color(224, 224, 224);
        Color primaryDark = new Color(25, 118, 210);
        Color primaryLight = new Color(144, 202, 249);

        // ViewModel & Data
        HomeViewModel homeViewModel = new HomeViewModel();
        StorageManager.load(homeViewModel);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> StorageManager.save(homeViewModel)));

        // Views
        homeView = new HomeView(cardLayout, contentPanel, homeViewModel,
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

        HomeIncomeView incomeView = new HomeIncomeView(
                cardLayout, contentPanel, homeViewModel,
                primaryColor, accentColor, textColor, darkTextColor,
                backgroundColor, cardColor, dividerColor, primaryDark);

        StatisticView statisticView = new StatisticView(backgroundColor, cardColor, darkTextColor, primaryDark);
        NotificationView notificationView = new NotificationView(backgroundColor, cardColor, darkTextColor, dividerColor, accentColor);
        ScheduleView scheduleView = new ScheduleView(backgroundColor, cardColor, primaryDark, primaryColor, textColor);
        SettingsView settingsView = new SettingsView(backgroundColor, cardColor, dividerColor, darkTextColor, primaryDark);

        // Add expense detail navigation
        homeView.setExpenseClickListener(expense -> {
            homeViewModel.setSelectedExpense(expense);
            expenseView.update(homeViewModel);
            cardLayout.show(contentPanel, "ExpenseDetails");
        });

        // Add income detail navigation
        homeView.setIncomeClickListener(income -> {
            homeViewModel.setSelectedIncome(income);
            incomeView.update(homeViewModel);
            cardLayout.show(contentPanel, "IncomeDetails");
        });

        // Add views to layout
        contentPanel.add(homeView.getPanel(), "Home");
        contentPanel.add(addTransactionView.getPanel(), "AddOptions");
        contentPanel.add(addIncomeView.getPanel(), "AddIncome");
        contentPanel.add(addExpenseView.getPanel(), "AddExpense");
        contentPanel.add(setLimitView.getPanel(), "SetLimit");
        contentPanel.add(expenseView.getPanel(), "ExpenseDetails");
        contentPanel.add(incomeView, "IncomeDetails"); // now this works!
        contentPanel.add(statisticView.getPanel(), "Statistics");
        contentPanel.add(notificationView.getPanel(), "Notification");
        contentPanel.add(scheduleView.getPanel(), "Schedule");
        contentPanel.add(settingsView.getPanel(), "Settings");

        // Show initial view
        cardLayout.show(contentPanel, "Home");

        // Layout: combine nav and views
        JPanel rootPanel = new JPanel(new BorderLayout());
        rootPanel.add(contentPanel, BorderLayout.CENTER);
        rootPanel.add(createNavBar(), BorderLayout.SOUTH);

        frame.setContentPane(rootPanel);
        frame.setVisible(true);
    }

    private JPanel createNavBar() {
        JPanel navBar = new JPanel(new GridLayout(1, 5));
        navBar.setBackground(new Color(250, 250, 250));
        String[] icons = {"🏠", "📊", "🔔", "📅", "⚙️"};
        String[] views = {"Home", "Statistics", "Notification", "Schedule", "Settings"};

        for (int i = 0; i < icons.length; i++) {
            String view = views[i];
            JButton button = new JButton(icons[i]);
            button.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setToolTipText(view); // helps users understand icons
            button.addActionListener(e -> {
                if (view.equals("Home")) {
                    homeView.refresh();
                }
                cardLayout.show(contentPanel, view);
            });
            navBar.add(button);
        }
        return navBar;
    }
}
