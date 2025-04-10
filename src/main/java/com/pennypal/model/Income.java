package main.java.com.pennypal.model;

public class Income {
    private String name;
    private double amount;
    private String date;
    private String time;
    private String description;

    public Income(double amount, String date) {
        this.name = "Income";
        this.amount = amount;
        this.date = date;
        this.time = "";
        this.description = "";
    }

    public Income(String name, double amount, String date) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.time = "";
        this.description = "";
    }

    public Income(String name, double amount, String date, String time, String description) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.description = description;
    }

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

    public void setTime(String time) {
        this.time = time;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
