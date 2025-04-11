package main.java.com.pennypal.model;

public class Limit {
    private double amount;
    private String type; // e.g., "Daily", "Weekly", "Monthly"

    public Limit(double amount, String type) {
        this.amount = amount;
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }
}
