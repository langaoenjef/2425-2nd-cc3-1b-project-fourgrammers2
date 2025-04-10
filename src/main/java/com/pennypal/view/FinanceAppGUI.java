package main.java.com.pennypal.view;

import javax.swing.*;

import main.java.com.pennypal.viewmodel.HomeViewModel;
import main.java.com.pennypal.storage.StorageManager;
import java.awt.*;

public class FinanceAppGUI {
    private JFrame frame;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private HomeView homeView; // 🔁 Store reference to HomeView

    public FinanceAppGUI() {
        frame = new JFrame("Penny - Expense Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 640);
        frame.setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Shared color palette
        Color primaryColor = new Color(98, 0, 238);
        Color primaryDark = new Color(69, 39, 123);
        Color primaryLight = new Color(225, 190, 231);
        Color accentColor = new Color(255, 111, 0);
        Color textColor = Color.WHITE;
        Color darkTextColor = new Color(33, 33, 33);
        Color backgroundColor = new Color(250, 250, 250);
        Color cardColor = Color.WHITE;
        Color dividerColor = new Color(224, 224, 224);

        // Create view model and load data
        HomeViewModel homeViewModel = new HomeViewModel();
        StorageManager.load(homeViewModel);

        // Create views
        homeView = new HomeView(cardLayout, contentPanel, homeViewModel, // 🔁 stored
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
                backgroundColor, cardColor, dividerColor, primaryDark
        );

        HomeAddExpenseView addExpenseView = new HomeAddExpenseView(
                cardLayout, contentPanel, homeViewModel,
                primaryColor, accentColor, textColor, darkTextColor,
                backgroundColor, cardColor, dividerColor, primaryDark
        );

        HomeSetLimitView setLimitView = new HomeSetLimitView(
                cardLayout, contentPanel, homeViewModel,
                primaryColor, accentColor, textColor, darkTextColor,
                backgroundColor, cardColor, dividerColor, primaryDark
        );

        HomeExpenseView expenseView = new HomeExpenseView(
                cardLayout, contentPanel, homeViewModel,
                primaryColor, accentColor, textColor, darkTextColor,
                backgroundColor, cardColor, dividerColor, primaryDark
        );

        StatisticView statisticView = new StatisticView(backgroundColor, cardColor, darkTextColor, primaryDark);
        NotificationView notificationView = new NotificationView(backgroundColor, cardColor, darkTextColor, dividerColor, accentColor);
        ScheduleView scheduleView = new ScheduleView(backgroundColor, cardColor, primaryDark, primaryColor, textColor);
        SettingsView settingsView = new SettingsView(backgroundColor, cardColor, dividerColor, darkTextColor, primaryDark);

        // Add panels to CardLayout
        contentPanel.add(homeView.getPanel(), "Home");
        contentPanel.add(addTransactionView.getPanel(), "AddOptions");
        contentPanel.add(addIncomeView.getPanel(), "AddIncome");
        contentPanel.add(addExpenseView.getPanel(), "AddExpense");
        contentPanel.add(setLimitView.getPanel(), "SetLimit");
        contentPanel.add(expenseView.getPanel(), "ExpenseDetails");
        contentPanel.add(statisticView.getPanel(), "Statistics");
        contentPanel.add(notificationView.getPanel(), "Notification");
        contentPanel.add(scheduleView.getPanel(), "Schedule");
        contentPanel.add(settingsView.getPanel(), "Settings");

        // Add components to frame
        frame.add(contentPanel, BorderLayout.CENTER);
        frame.add(createNavBar(), BorderLayout.SOUTH);
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
            button.addActionListener(e -> {
                if (view.equals("Home")) {
                    homeView.refresh(); // ✅ Refresh before showing
                }
                cardLayout.show(contentPanel, view);
            });
            navBar.add(button);
        }
        return navBar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new FinanceAppGUI();
        });
    }
}
