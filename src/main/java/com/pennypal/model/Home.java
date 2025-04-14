package main.java.com.pennypal.model;

import java.util.ArrayList;
import java.util.List;

public class Home {
    private List<Income> incomes;
    private List<Expense> expenses;
    private double balance;
    private Limit limit;

    private Expense selectedExpense;
    private Income selectedIncome; // ✅ Add this

    public Home() {
        this.incomes = new ArrayList<>();
        this.expenses = new ArrayList<>();
        this.balance = 0;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Limit getLimit() {
        return limit;
    }

    public void setLimit(Limit limit) {
        this.limit = limit;
    }

    public Expense getSelectedExpense() {
        return selectedExpense;
    }

    public void setSelectedExpense(Expense selectedExpense) {
        this.selectedExpense = selectedExpense;
    }

    public Income getSelectedIncome() { // ✅ Add this
        return selectedIncome;
    }

    public void setSelectedIncome(Income selectedIncome) { // ✅ Add this
        this.selectedIncome = selectedIncome;
    }
}
