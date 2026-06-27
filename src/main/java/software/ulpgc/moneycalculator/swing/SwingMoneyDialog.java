package software.ulpgc.moneycalculator.swing;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.Money;
import software.ulpgc.moneycalculator.ui.MoneyDialog;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {
    private final JTextField amountField;
    private final JComboBox<Currency> currencySelector;

    public SwingMoneyDialog() {
        this.setLayout(new FlowLayout());

        this.amountField = new JTextField("1", 10);
        this.add(amountField);

        this.currencySelector = new JComboBox<>();
        this.add(currencySelector);
    }

    @Override
    public MoneyDialog define(Money money) {
        amountField.setText(String.valueOf(money.getAmount()));
        currencySelector.setSelectedItem(money.getCurrency());
        return this;
    }

    public void define(List<Currency> currencies) {
        currencySelector.removeAllItems();
        for (Currency currency : currencies) {
            currencySelector.addItem(currency);
        }
    }

    @Override
    public Money get() {
        String text = amountField.getText();
        if (text.isEmpty()) return new Money(0, (Currency) currencySelector.getSelectedItem());

        try {
            double amount = Double.parseDouble(text);
            return new Money(amount, (Currency) currencySelector.getSelectedItem());
        } catch (NumberFormatException e) {
            return new Money(0, (Currency) currencySelector.getSelectedItem());
        }
    }

    @Override
    public void setCurrency(Currency currency) {
        currencySelector.setSelectedItem(currency);
    }

}
