package main.java.com.pennypal.viewmodel;

import main.java.com.pennypal.model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.stream.Collectors;

public class HomeViewModel {
    private Home data;
    private Expense selectedExpense;

    private static final String DATA_FILE = "data.json";
    private Gson gson = new Gson();

    public HomeViewModel() {
        loadData();
        if (data == null) {
            this.data = new Home();
        }
    }

    public Home getData() {
        return data;
    }

    public double getBalance() {
        return data.getBalance();
    }

    public void addIncome(Income income) {
        data.getIncomes().add(income);
        data.setBalance(data.getBalance() + income.getAmount());
        saveData();
    }

    public void addIncome(double amount, String date) {
        Income income = new Income(amount, date);
        addIncome(income);
    }

    public void addExpense(String name, double amount) {
        addExpense(name, amount, java.time.LocalDate.now().toString());
    }

    public void addExpense(String name, double amount, String date) {
        Expense expense = new Expense(name, amount, date);
        data.getExpenses().add(0, expense);
        data.setBalance(data.getBalance() - amount);
        saveData();
    }

    public void addExpense(Expense expense) {
        data.getExpenses().add(0, expense);
        data.setBalance(data.getBalance() - expense.getAmount());
        saveData();
    }
    

    public List<Expense> getRecentExpenses() {
        List<Expense> allExpenses = data.getExpenses();
        int fromIndex = Math.max(allExpenses.size() - 3, 0);
        return allExpenses.subList(fromIndex, allExpenses.size());
    }

    public List<Income> getRecentIncomes() {
        List<Income> allIncomes = data.getIncomes();
        int fromIndex = Math.max(allIncomes.size() - 3, 0);
        return allIncomes.subList(fromIndex, allIncomes.size());
    }

    public void setLimit(Limit limit) {
        data.setLimit(limit);
        saveData();
    }

    public Limit getLimit() {
        return data.getLimit();
    }

    public double getTotalBalance() {
        return data.getBalance();
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
        this.data.setLimit(new Limit(amount, type));
        saveData();
    }

    public double getTotalExpenseToday() {
        LocalDate today = LocalDate.now();
        return data.getExpenses().stream()
                .filter(expense -> LocalDate.parse(expense.getDate()).isEqual(today))
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    public double getTotalExpenseThisWeek() {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.minusDays(today.getDayOfWeek().getValue() - 1);
        return data.getExpenses().stream()
                .filter(expense -> {
                    LocalDate date = LocalDate.parse(expense.getDate());
                    return !date.isBefore(startOfWeek) && !date.isAfter(today);
                })
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    public double getTotalExpenseThisMonth() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        return data.getExpenses().stream()
                .filter(expense -> {
                    LocalDate date = LocalDate.parse(expense.getDate());
                    return !date.isBefore(firstDayOfMonth) && !date.isAfter(today);
                })
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    public double getTotalIncomeToday() {
        LocalDate today = LocalDate.now();
        return data.getIncomes().stream()
                .filter(income -> LocalDate.parse(income.getDate()).isEqual(today))
                .mapToDouble(Income::getAmount)
                .sum();
    }

    public double getTotalIncomeThisWeek() {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.minusDays(today.getDayOfWeek().getValue() - 1);
        return data.getIncomes().stream()
                .filter(income -> {
                    LocalDate date = LocalDate.parse(income.getDate());
                    return !date.isBefore(startOfWeek) && !date.isAfter(today);
                })
                .mapToDouble(Income::getAmount)
                .sum();
    }

    public double getTotalIncomeThisMonth() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        return data.getIncomes().stream()
                .filter(income -> {
                    LocalDate date = LocalDate.parse(income.getDate());
                    return !date.isBefore(firstDayOfMonth) && !date.isAfter(today);
                })
                .mapToDouble(Income::getAmount)
                .sum();
    }

    public void setSelectedIncome(Income income) {
        data.setSelectedIncome(income);
    }

    public Income getSelectedIncome() {
        return data.getSelectedIncome();
    }

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
}
