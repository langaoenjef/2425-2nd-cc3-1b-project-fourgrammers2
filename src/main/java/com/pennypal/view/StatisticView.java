package main.java.com.pennypal.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StatisticView {
    private JPanel panel;
    private Color backgroundColor, cardColor, darkTextColor, primaryDark;

    public StatisticView(Color backgroundColor, Color cardColor, Color darkTextColor, Color primaryDark) {
        this.backgroundColor = backgroundColor;
        this.cardColor = cardColor;
        this.darkTextColor = darkTextColor;
        this.primaryDark = primaryDark;
        this.panel = createStatisticsPanel();
    }

    public JPanel getPanel() {
        return panel;
    }

    private JPanel createStatisticsPanel() {
        JPanel stats = new JPanel(new BorderLayout());
        stats.setBackground(backgroundColor);

        JLabel title = new JLabel("Statistics", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(primaryDark);
        title.setBorder(new EmptyBorder(20, 0, 20, 0));
        stats.add(title, BorderLayout.NORTH);

        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int width = getWidth();
                int height = getHeight();
                int[] values = {30, 50, 80, 40, 60};
                String[] categories = {"Food", "Bills", "Transport", "Shopping", "Other"};
                Color[] colors = {new Color(76, 175, 80), new Color(244, 67, 54),
                                  new Color(33, 150, 243), new Color(255, 152, 0),
                                  new Color(156, 39, 176)};
                int barWidth = width / (values.length + 2);
                int maxValue = 100;
                g2.setColor(new Color(0, 0, 0, 20));
                for (int i = 0; i <= maxValue; i += 20) {
                    int y = height - 50 - (int)((double)i / maxValue * (height - 100));
                    g2.drawLine(0, y, width, y);
                    g2.drawString("$" + i, 5, y - 5);
                }
                for (int i = 0; i < values.length; i++) {
                    int barHeight = (int) ((double) values[i] / maxValue * (height - 100));
                    int x = (i + 1) * barWidth;
                    int y = height - 50 - barHeight;
                    g2.setColor(new Color(0, 0, 0, 30));
                    g2.fillRoundRect(x + 2, y + 2, barWidth - 20, barHeight, 10, 10);
                    g2.setColor(colors[i]);
                    g2.fillRoundRect(x, y, barWidth - 20, barHeight, 10, 10);
                    g2.setColor(darkTextColor);
                    g2.drawString("$" + values[i], x, y - 5);
                    g2.drawString(categories[i], x, height - 30);
                }
            }
        };
        chartPanel.setPreferredSize(new Dimension(300, 300));
        chartPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        stats.add(chartPanel, BorderLayout.CENTER);
        return stats;
    }
}
