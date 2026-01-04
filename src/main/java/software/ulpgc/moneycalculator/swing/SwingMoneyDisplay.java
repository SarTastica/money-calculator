package software.ulpgc.moneycalculator.swing;

import software.ulpgc.moneycalculator.model.Money;
import software.ulpgc.moneycalculator.ui.MoneyDisplay;

import javax.swing.*;
import java.awt.*;

public class SwingMoneyDisplay extends JPanel implements MoneyDisplay {
    private final JLabel label;

    public SwingMoneyDisplay() {
        this.setLayout(new FlowLayout());
        this.label = new JLabel("---");
        this.label.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(label);
    }

    @Override
    public void show(Money money) {
        this.label.setText(money.toString());
    }
}
