package main.java.com.pennypal.viewmodel;

import main.java.com.pennypal.model.PlannedPayment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleViewModel {
    private List<PlannedPayment> plannedPayments;

    public ScheduleViewModel() {
        plannedPayments = new ArrayList<>();
    }

    public void addPlannedPayment(PlannedPayment payment) {
        plannedPayments.add(payment);
    }

    public void removePlannedPayment(PlannedPayment payment) {
        plannedPayments.remove(payment);
    }

    public List<PlannedPayment> getPaymentsForDate(LocalDate date) {
        return plannedPayments.stream()
                .filter(payment -> payment.getDueDate().equals(date))
                .collect(Collectors.toList());
    }
    
    public List<PlannedPayment> getPaymentsForDate(LocalTime time) {
        return plannedPayments.stream()
                .filter(payment -> payment.getDueTime().equals(time))
                .collect(Collectors.toList());
    }

    public List<PlannedPayment> getAllPlannedPayments() {
        return plannedPayments;
    }
}
