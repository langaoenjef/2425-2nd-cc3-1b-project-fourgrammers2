package main.java.com.pennypal.viewmodel;

import main.java.com.pennypal.model.*;

public class SetLimitViewModel {
    private final HomeViewModel homeViewModel;

    public SetLimitViewModel(HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
    }

    public void setLimit(double amount, String frequency) {
        Limit limit = new Limit(amount, frequency);
        homeViewModel.setLimit(limit);
    }
}
