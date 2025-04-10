package main.java.com.pennypal.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SettingsView {
    private JPanel panel;
    private Color backgroundColor, cardColor, dividerColor, darkTextColor, primaryDark;

    public SettingsView(Color backgroundColor, Color cardColor, Color dividerColor, Color darkTextColor, Color primaryDark) {
        this.backgroundColor = backgroundColor;
        this.cardColor = cardColor;
        this.dividerColor = dividerColor;
        this.darkTextColor = darkTextColor;
        this.primaryDark = primaryDark;
        this.panel = createSettingsPanel();
    }

    public JPanel getPanel() {
        return panel;
    }

    private JPanel createSettingsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(backgroundColor);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Settings");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(primaryDark);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        String[] settings = {"Categories", "Appearance", "Notifications", "Security", "About"};
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

            panel.add(settingPanel);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        return panel;
    }
}
