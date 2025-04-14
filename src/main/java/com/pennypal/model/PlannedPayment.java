package main.java.com.pennypal.model;


import java.time.LocalDate;
import java.time.LocalTime;

public class PlannedPayment {
    private String title;
    private double amount;
    private LocalDate dueDate;
    private LocalTime dueTime;
    private boolean isCompleted;

    public PlannedPayment(String title, double amount, LocalDate dueDate, LocalTime dueTime) {
        this.title = title;
        this.amount = amount;
        this.dueTime = dueTime;
        this.dueDate = dueDate;
        this.isCompleted = false;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
    
    public LocalTime getDueTime() {
        return dueTime;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void toggleCompleted() {
        isCompleted = !isCompleted;
    }

    @Override
    public String toString() {
        return (isCompleted ? "[✔] " : "[ ] ") + title + " - ₱" + String.format("%.2f", amount) + " - " + dueTime;
    }
}
