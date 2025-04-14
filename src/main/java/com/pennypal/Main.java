package main.java.com.pennypal;

import javax.swing.*;
import main.java.com.pennypal.view.FinanceAppGUI;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FinanceAppGUI::new);
    }
}
