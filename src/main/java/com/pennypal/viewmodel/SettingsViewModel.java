package main.java.com.pennypal.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class SettingsViewModel {
    private List<String> categories;
    private List<String> notifications;
    private boolean notificationsEnabled;

    public SettingsViewModel() {
        this.categories = new ArrayList<>();
        this.notifications = new ArrayList<>();
        this.notificationsEnabled = true; // default ON
    }

    // Categories
    public List<String> getCategories() {
        return categories;
    }

    public void addCategory(String category) {
        if (!category.trim().isEmpty() && !categories.contains(category)) {
            categories.add(category);
        }
    }

    public void removeCategory(String category) {
        categories.remove(category);
    }

    // Notifications
    public List<String> getNotifications() {
        return notifications;
    }

    public void addNotification(String message) {
        if (notificationsEnabled) {
            notifications.add(message);
        }
    }

    public void removeNotification(String message) {
        notifications.remove(message);
    }

    public boolean isNotificationsEnabled() {
        return notificationsEnabled;
    }

    public void toggleNotifications(boolean enabled) {
        this.notificationsEnabled = enabled;
    }

    public String getTermsAndRegulations() {
        return "Terms and Regulations:\n\n1. Your data is secure.\n2. Respect budget limits.\n3. Features are subject to updates.";
    }
}
