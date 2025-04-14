package main.java.com.pennypal.viewmodel;

import main.java.com.pennypal.model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;

import java.util.List;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class HomeViewModel {
    private Home data;
    private Expense selectedExpense;

    private static final String DATA_FILE = "data.json";
    private Gson gson = new Gson();

    public void copyFrom(HomeViewModel other) {
        if (other == null || other.data == null) return;
        this.data = other.data;
        this.selectedExpense = other.selectedExpense;
    }
<<<<<<< HEAD
=======
    
>>>>>>> 028261e2002c4527a6dcfe97d80b3cc405ead52f

    public HomeViewModel() {
        loadData();
        if (data == null) {
            this.data = new Home();
        }
    }

    public HomeViewModel(Home data) {
        this.data = (data != null) ? data : new Home();
<<<<<<< HEAD
    }
=======
    }    
>>>>>>> 028261e2002c4527a6dcfe97d80b3cc405ead52f

    public Home getData() {
        return data;
    }

    public double getBalance() {
        return data.getBalance();
    }

    public void addIncome(Income income) {
<<<<<<< HEAD
        data.getIncomes().add(0, income);
        data.setBalance(data.getBalance() + income.getAmount());
        saveData();
    }
=======
        data.getIncomes().add(0, income); // 🔁 add at index 0
        data.setBalance(data.getBalance() + income.getAmount());
        saveData();
    }    
>>>>>>> 028261e2002c4527a6dcfe97d80b3cc405ead52f

    public void addIncome(double amount, String date) {
        Income income = new Income(amount, date);
        addIncome(income);
    }

    public void addExpense(String name, double amount, String date, String time, String category, String description) {
        Expense expense = new Expense(name, amount, date, time, category, description);
        data.getExpenses().add(0, expense);
        data.setBalance(data.getBalance() - amount);
        saveData();
<<<<<<< HEAD
    }
=======
    }    
>>>>>>> 028261e2002c4527a6dcfe97d80b3cc405ead52f

    public void addExpense(Expense expense) {
        data.getExpenses().add(0, expense);
        data.setBalance(data.getBalance() - expense.getAmount());
        saveData();
<<<<<<< HEAD
    }

    public List<Expense> getRecentExpenses() {
        return data.getExpenses();
    }

    public List<Income> getRecentIncomes() {
        return data.getIncomes();
    }

=======
    }    

    public List<Expense> getRecentExpenses() {
        return data.getExpenses(); // returns all expenses in order
    }
    
    public List<Income> getRecentIncomes() {
        return data.getIncomes(); // returns all incomes in order
    }
    
>>>>>>> 028261e2002c4527a6dcfe97d80b3cc405ead52f
    public void setLimit(Limit limit) {
        data.setLimit(limit);
        saveData();
    }

    public Limit getLimit() {
        return data.getLimit();
    }

    public boolean hasLimit() {
        return data.getLimit() != null;
    }

    public double getLimitAmount() {
        Limit limit = data.getLimit();
        return (limit != null) ? limit.getAmount() : 0;
    }

    public String getLimitType() {
        Limit limit = data.getLimit();
        return (limit != null) ? limit.getType() : "";
    }

    public void setSelectedExpense(Expense expense) {
        this.selectedExpense = expense;
    }

    public Expense getSelectedExpense() {
        return selectedExpense;
    }

    public void setSpendingLimit(double amount, String type) {
        data.setLimit(new Limit(amount, type));
        saveData();
    }

    public double getTotalExpenseToday() {
        LocalDate today = LocalDate.now();
        return data.getExpenses().stream()
                .filter(expense -> {
                    try {
                        return LocalDate.parse(expense.getDate()).isEqual(today);
                    } catch (Exception e) {
                        return false;
                    }
                })
                .mapToDouble(Expense::getAmount)
                .sum();
    }
<<<<<<< HEAD

=======
    
>>>>>>> 028261e2002c4527a6dcfe97d80b3cc405ead52f
    public double getTotalExpenseThisWeek() {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.minusDays(today.getDayOfWeek().getValue() - 1);
        return data.getExpenses().stream()
                .filter(expense -> {
                    try {
                        LocalDate date = LocalDate.parse(expense.getDate());
                        return !date.isBefore(startOfWeek) && !date.isAfter(today);
                    } catch (Exception e) {
                        return false;
                    }
                })
                .mapToDouble(Expense::getAmount)
                .sum();
    }
<<<<<<< HEAD

=======
    
>>>>>>> 028261e2002c4527a6dcfe97d80b3cc405ead52f
    public double getTotalExpenseThisMonth() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        return data.getExpenses().stream()
                .filter(expense -> {
                    try {
                        LocalDate date = LocalDate.parse(expense.getDate());
                        return !date.isBefore(firstDayOfMonth) && !date.isAfter(today);
                    } catch (Exception e) {
                        return false;
                    }
                })
                .mapToDouble(Expense::getAmount)
                .sum();
    }
<<<<<<< HEAD

=======
    
>>>>>>> 028261e2002c4527a6dcfe97d80b3cc405ead52f
    public double getTotalIncomeToday() {
        LocalDate today = LocalDate.now();
        return data.getIncomes().stream()
                .filter(income -> {
                    try {
                        return LocalDate.parse(income.getDate()).isEqual(today);
                    } catch (Exception e) {
                        return false;
                    }
                })
                .mapToDouble(Income::getAmount)
                .sum();
    }
<<<<<<< HEAD

=======
    
>>>>>>> 028261e2002c4527a6dcfe97d80b3cc405ead52f
    public double getTotalIncomeThisWeek() {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.minusDays(today.getDayOfWeek().getValue() - 1);
        return data.getIncomes().stream()
                .filter(income -> {
                    try {
                        LocalDate date = LocalDate.parse(income.getDate());
                        return !date.isBefore(startOfWeek) && !date.isAfter(today);
                    } catch (Exception e) {
                        return false;
                    }
                })
                .mapToDouble(Income::getAmount)
                .sum();
    }
<<<<<<< HEAD

=======
    
>>>>>>> 028261e2002c4527a6dcfe97d80b3cc405ead52f
    public double getTotalIncomeThisMonth() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        return data.getIncomes().stream()
                .filter(income -> {
                    try {
                        LocalDate date = LocalDate.parse(income.getDate());
                        return !date.isBefore(firstDayOfMonth) && !date.isAfter(today);
                    } catch (Exception e) {
                        return false;
                    }
                })
                .mapToDouble(Income::getAmount)
                .sum();
<<<<<<< HEAD
    }
=======
    }    
>>>>>>> 028261e2002c4527a6dcfe97d80b3cc405ead52f

    public void setSelectedIncome(Income income) {
        data.setSelectedIncome(income);
        saveData();
    }

    public Income getSelectedIncome() {
        return data.getSelectedIncome();
    }

<<<<<<< HEAD
    public void deleteSelectedIncome() {
        Income selected = data.getSelectedIncome();
        if (selected != null && data.getIncomes().remove(selected)) {
            data.setBalance(data.getBalance() - selected.getAmount());
            data.setSelectedIncome(null);
            saveData();
        }
    }

    public void deleteSelectedExpense() {
        if (selectedExpense != null && data.getExpenses().remove(selectedExpense)) {
            data.setBalance(data.getBalance() + selectedExpense.getAmount());
            selectedExpense = null;
            saveData();
        }
    }    

=======
>>>>>>> 028261e2002c4527a6dcfe97d80b3cc405ead52f
    private void saveData() {
        try (FileWriter writer = new FileWriter(DATA_FILE)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        try (FileReader reader = new FileReader(DATA_FILE)) {
            Type type = new TypeToken<Home>() {}.getType();
            this.data = gson.fromJson(reader, type);
        } catch (IOException e) {
            this.data = new Home();
        }
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 028261e2002c4527a6dcfe97d80b3cc405ead52f
