package main.java.com.pennypal.model;

public class Expense {
    private String name;
    private double amount;
    private String date;
    private String category;
    private String description;

    public Expense() {
        // Needed for Gson
    }

    // Constructor used when full details are available
    public Expense(String name, double amount, String date, String category, String description) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.description = description;
    }

    // Constructor used for minimal expense info (like name and amount only)
    public Expense(String name, double amount, String date) {
        this.name = name;
        this.amount = amount;
        this.date = date;
    }
    
    // Getters and setters
    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
