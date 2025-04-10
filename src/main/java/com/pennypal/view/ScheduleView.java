package main.java.com.pennypal.view;

import main.java.com.pennypal.model.PlannedPayment;
import main.java.com.pennypal.viewmodel.ScheduleViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;

public class ScheduleView {
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel calendarPanel;

    private ScheduleViewModel viewModel;
    private Color primaryDark, primaryColor, textColor;

    private YearMonth currentMonth;
    private JLabel monthLabel;

    public ScheduleView(Color bg, Color cardColor, Color primaryDark, Color primaryColor, Color textColor) {
        this.primaryDark = primaryDark;
        this.primaryColor = primaryColor;
        this.textColor = textColor;

        this.viewModel = new ScheduleViewModel();
        this.currentMonth = YearMonth.now();

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(bg);

        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(bg);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buildHeader();
        buildCalendar(currentMonth);
    }

    private void buildHeader() {
        JButton prevButton = new JButton("◀");
        prevButton.setFocusPainted(false);
        prevButton.setContentAreaFilled(false);
        prevButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        prevButton.addActionListener(e -> {
            currentMonth = currentMonth.minusMonths(1);
            updateCalendar();
        });

        JButton nextButton = new JButton("▶");
        nextButton.setFocusPainted(false);
        nextButton.setContentAreaFilled(false);
        nextButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nextButton.addActionListener(e -> {
            currentMonth = currentMonth.plusMonths(1);
            updateCalendar();
        });

        JButton addButton = new JButton("+");
        addButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        addButton.setFocusPainted(false);
        addButton.setContentAreaFilled(false);
        addButton.addActionListener(e -> showAddPaymentDialog());

        monthLabel = new JLabel();
        monthLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        monthLabel.setForeground(primaryDark);
        monthLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        leftPanel.setOpaque(false);
        leftPanel.add(prevButton);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        rightPanel.setOpaque(false);
        rightPanel.add(addButton);
        rightPanel.add(nextButton);

        headerPanel.add(leftPanel, BorderLayout.WEST);
        headerPanel.add(monthLabel, BorderLayout.CENTER);
        headerPanel.add(rightPanel, BorderLayout.EAST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
    }

    private void buildCalendar(YearMonth yearMonth) {
        if (calendarPanel != null) {
            mainPanel.remove(calendarPanel);
        }

        calendarPanel = new JPanel(new GridLayout(0, 7));
        calendarPanel.setBackground(Color.WHITE);
        calendarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        int startDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue(); // 1 = Monday
        int daysInMonth = yearMonth.lengthOfMonth();

        monthLabel.setText(yearMonth.getMonth() + " " + yearMonth.getYear());

        String[] days = {"S", "M", "T", "W", "T", "F", "S"};
        for (String day : days) {
            JLabel label = new JLabel(day, SwingConstants.CENTER);
            label.setFont(new Font("Segoe UI", Font.BOLD, 14));
            calendarPanel.add(label);
        }

        int blanks = (startDayOfWeek % 7);
        for (int i = 0; i < blanks; i++) {
            calendarPanel.add(new JLabel(""));
        }

        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate currentDate = yearMonth.atDay(day);
            JPanel dayCell = new JPanel(new BorderLayout());
            dayCell.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            dayCell.setBackground(Color.WHITE);

            JLabel dateLabel = new JLabel(String.valueOf(day), SwingConstants.RIGHT);
            dateLabel.setForeground(primaryDark);
            dayCell.add(dateLabel, BorderLayout.NORTH);

            List<PlannedPayment> payments = viewModel.getPaymentsForDate(currentDate);
            if (!payments.isEmpty()) {
                DefaultListModel<String> model = new DefaultListModel<>();
                for (PlannedPayment p : payments) model.addElement(p.toString());

                JList<String> list = new JList<>(model);
                list.setFont(new Font("Segoe UI", Font.PLAIN, 10));
                list.setForeground(primaryColor);
                list.setBackground(Color.WHITE);
                list.setSelectionBackground(new Color(128, 128, 128));

                list.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        if (SwingUtilities.isRightMouseButton(e)) {
                            int index = list.locationToIndex(e.getPoint());
                            if (index >= 0) {
                            list.setSelectedIndex(index);
                            PlannedPayment selected = payments.get(index);

                            JPopupMenu menu = new JPopupMenu();

                            JMenuItem toggleItem = new JMenuItem(
                                selected.isCompleted() ? "Mark as Pending" : "Mark as Completed"
                            );
                            toggleItem.addActionListener(ev -> {
                                selected.toggleCompleted();
                                updateCalendar();
                            });

                            JMenuItem removeItem = new JMenuItem("Remove");
                            removeItem.addActionListener(ev -> {
                                int confirm = JOptionPane.showConfirmDialog(null,
                                    "Are you sure you want to delete this payment?",
                                    "Confirm Removal", JOptionPane.YES_NO_OPTION);
                                if (confirm == JOptionPane.YES_OPTION) {
                                    viewModel.removePlannedPayment(selected);
                                    updateCalendar();
                                }
                            });

                            menu.add(toggleItem);
                            menu.add(removeItem);
                            menu.show(list, e.getX(), e.getY());
                        }
                    }
                }
            });


                JScrollPane scrollPane = new JScrollPane(list);
                scrollPane.setBorder(null);
                scrollPane.setPreferredSize(new Dimension(100, 35));
                dayCell.add(scrollPane, BorderLayout.CENTER);
            }

            calendarPanel.add(dayCell);
        }

        mainPanel.add(calendarPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void updateCalendar() {
        buildCalendar(currentMonth);
    }

    private void showAddPaymentDialog() {
        JTextField titleField = new JTextField();
        JTextField amountField = new JTextField();
        JTextField dateField = new JTextField("YYYY-MM-DD");
        JTextField timeField = new JTextField("HH:MM");

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Amount:"));
        panel.add(amountField);
        panel.add(new JLabel("Due Date:"));
        panel.add(dateField);
        panel.add(new JLabel("Set Time:"));
        panel.add(timeField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Planned Payment",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String title = titleField.getText();
                double amount = Double.parseDouble(amountField.getText());
                LocalDate dueDate = LocalDate.parse(dateField.getText());
                LocalTime dueTime = LocalTime.parse(timeField.getText());
                viewModel.addPlannedPayment(new PlannedPayment(title, amount, dueDate, dueTime));
                updateCalendar();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please check your fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public JPanel getPanel() {
        return mainPanel;
    }

    public ScheduleViewModel getViewModel() {
        return viewModel;
    }
}
