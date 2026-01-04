package software.ulpgc.moneycalculator.swing;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.ui.CurrencyDialog;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingCurrencyDialog extends JPanel implements CurrencyDialog {
    private final JComboBox<Currency> selector;

    public SwingCurrencyDialog() {
        this.setLayout(new FlowLayout());
        this.selector = new JComboBox<>();
        this.add(selector);
    }

    @Override
    public CurrencyDialog define(List<Currency> currencies) {
        selector.removeAllItems();
        for (Currency currency : currencies) {
            selector.addItem(currency);
        }
        return this;
    }

    @Override
    public Currency get() {
        return (Currency) selector.getSelectedItem();
    }
}
