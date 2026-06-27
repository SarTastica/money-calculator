package software.ulpgc.moneycalculator.ui;

import software.ulpgc.moneycalculator.model.Money;
import software.ulpgc.moneycalculator.model.Currency;

public interface MoneyDialog {
    MoneyDialog define(Money money);
    Money get();
    void setCurrency(Currency currency);
}
