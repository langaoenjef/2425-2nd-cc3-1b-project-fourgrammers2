package main.java.com.pennypal.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HomeAddTransactionView {
    private JPanel panel;
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private Color primaryColor, primaryDark, primaryLight, accentColor, textColor, darkTextColor, backgroundColor, cardColor;

    public HomeAddTransactionView(CardLayout cardLayout, JPanel contentPanel,
                                  Color primaryColor, Color primaryDark, Color primaryLight,
                                  Color accentColor, Color textColor, Color darkTextColor,
                                  Color backgroundColor, Color cardColor) {
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        this.primaryColor = primaryColor;
        this.primaryDark = primaryDark;
        this.primaryLight = primaryLight;
        this.accentColor = accentColor;
        this.textColor = textColor;
        this.darkTextColor = darkTextColor;
        this.backgroundColor = backgroundColor;
        this.cardColor = cardColor;
        this.panel = createAddOptionsPanel();
    }

    public JPanel getPanel() {
        return panel;
    }

    private JPanel createAddOptionsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(backgroundColor);
        panel.setBorder(new EmptyBorder(40, 20, 40, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel title = new JLabel("Add Transaction", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(primaryDark);
        panel.add(title, gbc);

        JButton backButton = new JButton("← Back");
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        backButton.setForeground(primaryColor);
        backButton.setContentAreaFilled(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> cardLayout.show(contentPanel, "Home"));
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(backButton, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(30, 0, 30, 0);
        panel.add(Box.createRigidArea(new Dimension(0, 30)), gbc);

        JButton incomeButton = createOptionButton("INCOME", new Color(76, 175, 80), Color.BLACK);
        JButton expenseButton = createOptionButton("EXPENSE", new Color(244, 67, 54), Color.BLACK);
        panel.add(incomeButton, gbc);
        panel.add(expenseButton, gbc);

        return panel;
    }

    private JButton createOptionButton(String text, Color bgColor, Color textColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 18));
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(15, 40, 15, 40));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }
}
