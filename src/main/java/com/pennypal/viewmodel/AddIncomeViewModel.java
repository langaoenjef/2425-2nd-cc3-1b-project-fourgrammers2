package main.java.com.pennypal.viewmodel;

import main.java.com.pennypal.model.*;

public class AddIncomeViewModel {
    private final HomeViewModel homeViewModel;

    public AddIncomeViewModel(HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
    }

    public void addIncome(double amount, String date) {
        Income income = new Income(amount, date);
        homeViewModel.addIncome(income);
    }
}
