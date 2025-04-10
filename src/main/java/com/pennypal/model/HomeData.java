package main.java.com.pennypal.model;
import java.util.ArrayList;
import java.util.List;

public class HomeData {
    private double totalIncome;
    private double totalExpenses;
    private double budgetLimit;
    private final List<String> recentTransactions;

    public HomeData() {
        this.recentTransactions = new ArrayList<>();
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public double getBudgetLimit() {
        return budgetLimit;
    }

    public List<String> getRecentTransactions() {
        return recentTransactions;
    }

    public void addIncome(double amount) {
        totalIncome += amount;
        recentTransactions.add(0, "+ $" + amount + " (Income)");
    }

    public void addExpense(double amount) {
        totalExpenses += amount;
        recentTransactions.add(0, "- $" + amount + " (Expense)");
    }

    public void setBudgetLimit(double limit) {
        this.budgetLimit = limit;
    }

    public double getRemainingBudget() {
        return budgetLimit - totalExpenses;
    }
}
