package main.java.com.pennypal.view;

import java.util.function.Consumer;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import main.java.com.pennypal.viewmodel.HomeViewModel;
import main.java.com.pennypal.model.Expense;
import main.java.com.pennypal.model.Income;

public class HomeView {
    private Consumer<Expense> expenseClickListener;
<<<<<<< HEAD
    private Consumer<Income> incomeClickListener;
=======
>>>>>>> 028261e2002c4527a6dcfe97d80b3cc405ead52f

    private JPanel panel;
    private JLabel balanceLabel;
    private JLabel limitLabel;
    private JLabel summaryLabel;
    private JPanel transactionsPanel;
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private HomeViewModel viewModel;
    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "PH"));

    private Color primaryColor, accentColor, textColor, darkTextColor;
    private Color backgroundColor, cardColor, dividerColor, primaryDark;

    private JButton expenseTab;
    private JButton incomeTab;
    private boolean showingExpenses = true;

    public HomeView(CardLayout cardLayout, JPanel contentPanel, HomeViewModel homeViewModel,
                    Color primaryColor, Color accentColor, Color textColor, Color darkTextColor,
                    Color backgroundColor, Color cardColor, Color dividerColor, Color primaryDark) {
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        this.viewModel = homeViewModel;

        this.primaryColor = primaryColor;
        this.accentColor = accentColor;
        this.textColor = textColor;
        this.darkTextColor = darkTextColor;
        this.backgroundColor = backgroundColor;
        this.cardColor = cardColor;
        this.dividerColor = dividerColor;
        this.primaryDark = primaryDark;

        this.panel = createHomePanel();
    }

    public JPanel getPanel() {
        return panel;
    }

    public void updateView() {
        balanceLabel.setText(currencyFormat.format(viewModel.getBalance()));
        limitLabel.setText("Limit: " + (viewModel.hasLimit() ? currencyFormat.format(viewModel.getLimitAmount()) + " (" + viewModel.getLimitType() + ")" : "Not set"));

        summaryLabel.setText("Today: " + currencyFormat.format(viewModel.getTotalExpenseToday()) +
                " | This Week: " + currencyFormat.format(viewModel.getTotalExpenseThisWeek()) +
                " | This Month: " + currencyFormat.format(viewModel.getTotalExpenseThisMonth()));

        transactionsPanel.removeAll();

        if (showingExpenses) {
            for (Expense expense : viewModel.getRecentExpenses()) {
                transactionsPanel.add(createExpenseItem(expense));
                transactionsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        } else {
            for (Income income : viewModel.getRecentIncomes()) {
                transactionsPanel.add(createIncomeItem(income));
                transactionsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }

        transactionsPanel.revalidate();
        transactionsPanel.repaint();
    }

    public void setExpenseClickListener(Consumer<Expense> listener) {
        this.expenseClickListener = listener;
    }

<<<<<<< HEAD
    public void setIncomeClickListener(Consumer<Income> listener) {
        this.incomeClickListener = listener;
    }

=======
>>>>>>> 028261e2002c4527a6dcfe97d80b3cc405ead52f
    private JPanel createHomePanel() {
        JPanel home = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(primaryColor);
        topPanel.setBorder(new EmptyBorder(20, 10, 20, 10));

        JLabel balanceTitle = new JLabel("CURRENT BALANCE", SwingConstants.CENTER);
        balanceTitle.setFont(new Font("SansSerif", Font.BOLD, 14));
        balanceTitle.setForeground(textColor);
        balanceTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        balanceLabel = new JLabel(currencyFormat.format(viewModel.getBalance()), SwingConstants.CENTER);
        balanceLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        balanceLabel.setForeground(textColor);
        balanceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        limitLabel = new JLabel("", SwingConstants.CENTER);
        limitLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        limitLabel.setForeground(textColor);
        limitLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        summaryLabel = new JLabel("", SwingConstants.CENTER);
        summaryLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        summaryLabel.setForeground(textColor);
        summaryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        topPanel.add(balanceTitle);
        topPanel.add(balanceLabel);
        topPanel.add(Box.createVerticalStrut(5));
        topPanel.add(limitLabel);
        topPanel.add(Box.createVerticalStrut(5));
        topPanel.add(summaryLabel);

        home.add(topPanel, BorderLayout.NORTH);

        transactionsPanel = new JPanel();
        transactionsPanel.setLayout(new BoxLayout(transactionsPanel, BoxLayout.Y_AXIS));
        transactionsPanel.setBackground(cardColor);
        transactionsPanel.setBorder(new EmptyBorder(10, 15, 10, 15));

        expenseTab = new JButton("Recent Expenses");
        incomeTab = new JButton("Recent Income");

        expenseTab.addActionListener(e -> {
            showingExpenses = true;
            updateView();
        });
        incomeTab.addActionListener(e -> {
            showingExpenses = false;
            updateView();
        });

        JPanel tabPanel = new JPanel(new GridLayout(1, 2));
        tabPanel.setBackground(backgroundColor);
        tabPanel.add(expenseTab);
        tabPanel.add(incomeTab);

        JLabel sectionLabel = new JLabel(" ");
        sectionLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        sectionLabel.setForeground(darkTextColor);

        transactionsPanel.add(sectionLabel);
        transactionsPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JScrollPane scrollPane = new JScrollPane(transactionsPanel);
        scrollPane.setBorder(null);

        JPanel centerWrapper = new JPanel(new BorderLayout());
        centerWrapper.setBackground(cardColor);
        centerWrapper.add(tabPanel, BorderLayout.NORTH);
        centerWrapper.add(scrollPane, BorderLayout.CENTER);

        home.add(centerWrapper, BorderLayout.CENTER);

        JButton addButton = new JButton("+");
        addButton.setFont(new Font("SansSerif", Font.BOLD, 30));
        addButton.setBackground(accentColor);
        addButton.setForeground(darkTextColor);
        addButton.setFocusPainted(false);
        addButton.setBorder(BorderFactory.createEmptyBorder());
        addButton.setPreferredSize(new Dimension(70, 70));
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addButton.addActionListener(e -> cardLayout.show(contentPanel, "AddOptions"));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(new Color(0, 0, 0, 0));
        bottomPanel.add(addButton);

        home.add(bottomPanel, BorderLayout.SOUTH);
        return home;
    }

    private JPanel createExpenseItem(Expense expense) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(cardColor);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, dividerColor),
                new EmptyBorder(10, 10, 10, 10)
        ));

        JLabel nameLabel = new JLabel(expense.getName());
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        nameLabel.setForeground(darkTextColor);

        JLabel subLabel = new JLabel(expense.getCategory() + " • " + expense.getDate());
        subLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subLabel.setForeground(darkTextColor);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(cardColor);
        leftPanel.add(nameLabel);
        leftPanel.add(subLabel);

        JLabel amountLabel = new JLabel(currencyFormat.format(expense.getAmount()));
        amountLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        amountLabel.setForeground(new Color(244, 67, 54));

        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(cardColor);
        content.add(leftPanel, BorderLayout.CENTER);
        content.add(amountLabel, BorderLayout.EAST);

        panel.add(content, BorderLayout.CENTER);

        panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (expenseClickListener != null) {
                    expenseClickListener.accept(expense);
                } else {
                    viewModel.setSelectedExpense(expense);
                    cardLayout.show(contentPanel, "ExpenseDetails");
                }
            }
        });

        return panel;
    }

    private JPanel createIncomeItem(Income income) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(cardColor);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, dividerColor),
                new EmptyBorder(10, 10, 10, 10)
        ));
<<<<<<< HEAD

        JLabel nameLabel = new JLabel(income.getName());
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        nameLabel.setForeground(darkTextColor);

        JLabel subLabel = new JLabel(income.getDate());
        subLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subLabel.setForeground(darkTextColor);

=======
    
        JLabel nameLabel = new JLabel(income.getName());
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        nameLabel.setForeground(darkTextColor);
    
        JLabel subLabel = new JLabel(income.getDate());  // only date
        subLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subLabel.setForeground(darkTextColor);
    
>>>>>>> 028261e2002c4527a6dcfe97d80b3cc405ead52f
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(cardColor);
        leftPanel.add(nameLabel);
        leftPanel.add(subLabel);
<<<<<<< HEAD

        JLabel amountLabel = new JLabel(currencyFormat.format(income.getAmount()));
        amountLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        amountLabel.setForeground(new Color(76, 175, 80));

=======
    
        JLabel amountLabel = new JLabel(currencyFormat.format(income.getAmount()));
        amountLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        amountLabel.setForeground(new Color(76, 175, 80)); // green for income
    
>>>>>>> 028261e2002c4527a6dcfe97d80b3cc405ead52f
        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(cardColor);
        content.add(leftPanel, BorderLayout.CENTER);
        content.add(amountLabel, BorderLayout.EAST);
<<<<<<< HEAD

        panel.add(content, BorderLayout.CENTER);

=======
    
        panel.add(content, BorderLayout.CENTER);
    
>>>>>>> 028261e2002c4527a6dcfe97d80b3cc405ead52f
        panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
<<<<<<< HEAD
                if (incomeClickListener != null) {
                    incomeClickListener.accept(income);
                } else {
                    viewModel.setSelectedIncome(income);
                    cardLayout.show(contentPanel, "IncomeDetails");
                }
            }
        });

        return panel;
    }
=======
                viewModel.setSelectedIncome(income);
                cardLayout.show(contentPanel, "IncomeDetails");
            }
        });
    
        return panel;
    }    
>>>>>>> 028261e2002c4527a6dcfe97d80b3cc405ead52f

    public void refresh() {
        updateView();
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 028261e2002c4527a6dcfe97d80b3cc405ead52f
