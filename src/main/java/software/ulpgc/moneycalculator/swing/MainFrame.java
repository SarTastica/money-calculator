package software.ulpgc.moneycalculator.swing;

import software.ulpgc.moneycalculator.control.Command;
import software.ulpgc.moneycalculator.ui.CurrencyDialog;
import software.ulpgc.moneycalculator.ui.MoneyDialog;
import software.ulpgc.moneycalculator.ui.MoneyDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private final Map<String, Command> commands = new HashMap<>();
    private final SwingMoneyDialog moneyDialog;
    private final SwingCurrencyDialog currencyDialog;
    private final SwingMoneyDisplay moneyDisplay;

    public MainFrame() {
        this.setTitle("Money Calculator");
        this.setSize(800, 200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        this.moneyDialog = new SwingMoneyDialog();
        this.currencyDialog = new SwingCurrencyDialog();
        this.moneyDisplay = new SwingMoneyDisplay();

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(e -> {
            if (commands.containsKey("exchange")) {
                commands.get("exchange").execute();
            }
        });

        JPanel centerPanel = new JPanel();
        centerPanel.add(moneyDialog);
        centerPanel.add(new JLabel(" to "));
        centerPanel.add(currencyDialog);
        centerPanel.add(calculateButton);

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(moneyDisplay, BorderLayout.SOUTH);
    }

    public void add(String name, Command command) {
        commands.put(name, command);
    }

    public MoneyDialog getMoneyDialog() {
        return moneyDialog;
    }

    public CurrencyDialog getCurrencyDialog() {
        return currencyDialog;
    }

    public MoneyDisplay getMoneyDisplay() {
        return moneyDisplay;
    }

    public SwingMoneyDialog getSwingMoneyDialog() {
        return moneyDialog;
    }
}
