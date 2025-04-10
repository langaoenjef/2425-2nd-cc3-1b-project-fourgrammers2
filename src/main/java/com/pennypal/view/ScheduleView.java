package main.java.com.pennypal.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class ScheduleView {
    private JPanel panel;
    private LocalDate currentDate;
    private Color backgroundColor, cardColor, primaryDark, primaryColor, textColor;

    public ScheduleView(Color backgroundColor, Color cardColor, Color primaryDark, Color primaryColor, Color textColor) {
        this.backgroundColor = backgroundColor;
        this.cardColor = cardColor;
        this.primaryDark = primaryDark;
        this.primaryColor = primaryColor;
        this.textColor = textColor;
        this.currentDate = LocalDate.now();
        this.panel = createSchedulePanel(); // Make sure this exists
    }

    public JPanel getPanel() {
        return panel;
    }

    private JPanel createSchedulePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(backgroundColor);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("Calendar", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(primaryDark);
        title.setBorder(new EmptyBorder(0, 0, 15, 0));
        panel.add(title, BorderLayout.NORTH);

        JPanel calendarPanel = new JPanel(new BorderLayout());
        calendarPanel.setBackground(cardColor);
        calendarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel monthPanel = new JPanel(new BorderLayout());
        monthPanel.setBackground(cardColor);
        monthPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 0));

        JButton prevMonth = new JButton("◀");
        JButton nextMonth = new JButton("▶");
        JLabel monthLabel = new JLabel(currentDate.format(DateTimeFormatter.ofPattern("MMMM yyyy")), SwingConstants.CENTER);

        prevMonth.setFont(new Font("SansSerif", Font.BOLD, 16));
        nextMonth.setFont(new Font("SansSerif", Font.BOLD, 16));
        monthLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

        prevMonth.setBackground(cardColor);
        nextMonth.setBackground(cardColor);
        prevMonth.setForeground(primaryDark);
        nextMonth.setForeground(primaryDark);
        monthLabel.setForeground(primaryDark);

        prevMonth.addActionListener(e -> {
            currentDate = currentDate.minusMonths(1);
            monthLabel.setText(currentDate.format(DateTimeFormatter.ofPattern("MMMM yyyy")));
        });
        nextMonth.addActionListener(e -> {
            currentDate = currentDate.plusMonths(1);
            monthLabel.setText(currentDate.format(DateTimeFormatter.ofPattern("MMMM yyyy")));
        });

        monthPanel.add(prevMonth, BorderLayout.WEST);
        monthPanel.add(monthLabel, BorderLayout.CENTER);
        monthPanel.add(nextMonth, BorderLayout.EAST);
        calendarPanel.add(monthPanel, BorderLayout.NORTH);

        panel.add(calendarPanel, BorderLayout.CENTER);
        return panel;
    }
}
