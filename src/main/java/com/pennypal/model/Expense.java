package main.java.com.pennypal.model;

public class Expense {
    private String name;
    private double amount;
    private String date;
    private String time;
    private String category;
    private String description;

    public Expense() {
        // Needed for Gson
    }

    // Constructor with all fields
    public Expense(String name, double amount, String date, String time, String category, String description) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.category = category;
        this.description = description;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
