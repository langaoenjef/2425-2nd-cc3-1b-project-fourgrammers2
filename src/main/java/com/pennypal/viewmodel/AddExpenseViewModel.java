package main.java.com.pennypal.viewmodel;

import main.java.com.pennypal.model.*;

public class AddExpenseViewModel {
    private final HomeViewModel homeViewModel;

    public AddExpenseViewModel(HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
    }

    public void addExpense(String name, double amount, String date, String category, String description) {
        Expense expense = new Expense(name, amount, date, category, description);
        homeViewModel.addExpense(expense);
    }
}
