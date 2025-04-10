package main.java.com.pennypal.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NotificationView {
    private JPanel panel;
    private Color backgroundColor, cardColor, darkTextColor, dividerColor, accentColor;

    public NotificationView(Color backgroundColor, Color cardColor, Color darkTextColor, Color dividerColor, Color accentColor) {
        this.backgroundColor = backgroundColor;
        this.cardColor = cardColor;
        this.darkTextColor = darkTextColor;
        this.dividerColor = dividerColor;
        this.accentColor = accentColor;
        this.panel = createNotificationPanel();
    }

    public JPanel getPanel() {
        return panel;
    }

    private JPanel createNotificationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(backgroundColor);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Notifications");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(darkTextColor);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        for (int i = 1; i <= 5; i++) {
            JPanel notifPanel = new JPanel(new BorderLayout());
            notifPanel.setBackground(cardColor);
            notifPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(dividerColor, 1),
                new EmptyBorder(15, 15, 15, 15)
            ));
            notifPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

            JCheckBox box = new JCheckBox();
            box.setBackground(cardColor);
            JLabel notifText = new JLabel("<html><b>Notification " + i + "</b><br/>Today at " + i + ":00 PM</html>");
            notifText.setFont(new Font("SansSerif", Font.PLAIN, 14));
            notifText.setForeground(darkTextColor);

            notifPanel.add(box, BorderLayout.WEST);
            notifPanel.add(notifText, BorderLayout.CENTER);
            panel.add(notifPanel);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        return panel;
    }
}
