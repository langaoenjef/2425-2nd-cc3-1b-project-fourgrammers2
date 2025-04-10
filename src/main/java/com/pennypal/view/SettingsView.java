package main.java.com.pennypal.view;

import main.java.com.pennypal.viewmodel.SettingsViewModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class SettingsView {
    private JPanel panel;
    private final SettingsViewModel viewModel;
    private final Color backgroundColor, cardColor, dividerColor, darkTextColor, primaryDark;

    public SettingsView(Color backgroundColor, Color cardColor, Color dividerColor, Color darkTextColor, Color primaryDark) {
        this.backgroundColor = backgroundColor;
        this.cardColor = cardColor;
        this.dividerColor = dividerColor;
        this.darkTextColor = darkTextColor;
        this.primaryDark = primaryDark;
        this.viewModel = new SettingsViewModel();
        this.panel = createSettingsPanel();
    }

    public JPanel getPanel() {
        return panel;
    }

    private JPanel createSettingsPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(backgroundColor);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Settings");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(primaryDark);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(title);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        String[] settings = {"Categories", "Notifications", "About"};
        for (String setting : settings) {
            JPanel settingPanel = new JPanel(new BorderLayout());
            settingPanel.setBackground(cardColor);
            settingPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(dividerColor, 1),
                new EmptyBorder(15, 15, 15, 15)
            ));
            settingPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
            settingPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            JLabel textLabel = new JLabel(setting);
            textLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
            textLabel.setForeground(darkTextColor);

            JLabel arrowLabel = new JLabel("❯");
            arrowLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
            arrowLabel.setForeground(primaryDark);

            settingPanel.add(textLabel, BorderLayout.CENTER);
            settingPanel.add(arrowLabel, BorderLayout.EAST);

            settingPanel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    switch (setting) {
                        case "Categories" -> showCategoriesDialog();
                        case "Notifications" -> showNotificationsDialog();
                        case "About" -> showAboutDialog();
                    }
                }
            });

            mainPanel.add(settingPanel);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        return mainPanel;
    }

    private void showCategoriesDialog() {
        JPanel panel = new JPanel(new BorderLayout());
        DefaultListModel<String> model = new DefaultListModel<>();
        viewModel.getCategories().forEach(model::addElement);
        JList<String> list = new JList<>(model);

        JTextField input = new JTextField();
        JButton addBtn = new JButton("Add");
        JButton removeBtn = new JButton("Remove Selected");

        addBtn.addActionListener(e -> {
            String text = input.getText();
            viewModel.addCategory(text);
            model.clear();
            viewModel.getCategories().forEach(model::addElement);
            input.setText("");
        });

        removeBtn.addActionListener(e -> {
            List<String> selected = list.getSelectedValuesList();
            selected.forEach(viewModel::removeCategory);
            model.clear();
            viewModel.getCategories().forEach(model::addElement);
        });

        JPanel controls = new JPanel(new GridLayout(1, 2));
        controls.add(addBtn);
        controls.add(removeBtn);

        panel.add(input, BorderLayout.NORTH);
        panel.add(new JScrollPane(list), BorderLayout.CENTER);
        panel.add(controls, BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(null, panel, "Manage Categories", JOptionPane.PLAIN_MESSAGE);
    }

    private void showNotificationsDialog() {
        JPanel panel = new JPanel(new BorderLayout());
        JCheckBox toggle = new JCheckBox("Enable Notifications", viewModel.isNotificationsEnabled());
        DefaultListModel<String> model = new DefaultListModel<>();
        viewModel.getNotifications().forEach(model::addElement);
        JList<String> list = new JList<>(model);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JButton deleteBtn = new JButton("Delete Selected");
        deleteBtn.addActionListener(e -> {
            List<String> selected = list.getSelectedValuesList();
            selected.forEach(viewModel::removeNotification);
            model.clear();
            viewModel.getNotifications().forEach(model::addElement);
        });

        toggle.addActionListener(e -> viewModel.toggleNotifications(toggle.isSelected()));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(toggle, BorderLayout.WEST);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(list), BorderLayout.CENTER);
        panel.add(deleteBtn, BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(null, panel, "Notifications", JOptionPane.PLAIN_MESSAGE);
    }

    private void showAboutDialog() {
        JTextArea textArea = new JTextArea(viewModel.getTermsAndRegulations());
        textArea.setEditable(false);
        textArea.setBackground(Color.WHITE);
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JOptionPane.showMessageDialog(null, new JScrollPane(textArea), "About", JOptionPane.INFORMATION_MESSAGE);
    }

    // You can hook these into transaction-related events:
    public void simulateEvent(String eventMessage) {
        viewModel.addNotification(eventMessage);
    }
}

