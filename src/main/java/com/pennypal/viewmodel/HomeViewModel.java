package main.java.com.pennypal.viewmodel;
import main.java.com.pennypal.model.HomeData;

public class HomeViewModel {
    private final HomeData model;

    public HomeViewModel(HomeData model) {
        this.model = model;
    }

    public void addIncome(double amount) {
        model.addIncome(amount);
    }

    public void addExpense(double amount) {
        model.addExpense(amount);
    }

    public void setBudgetLimit(double limit) {
        model.setBudgetLimit(limit);
    }

    public String getSummary() {
        return String.format(
            "Income: $%.2f | Expenses: $%.2f | Limit: $%.2f | Remaining: $%.2f",
            model.getTotalIncome(),
            model.getTotalExpenses(),
            model.getBudgetLimit(),
            model.getRemainingBudget()
        );
    }

    public void printRecentTransactions() {
        System.out.println("Recent Transactions:");
        for (String tx : model.getRecentTransactions()) {
            System.out.println("• " + tx);
        }
    }
}
