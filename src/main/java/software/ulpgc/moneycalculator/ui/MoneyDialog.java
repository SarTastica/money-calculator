package software.ulpgc.moneycalculator.ui;

import software.ulpgc.moneycalculator.model.Money;

public interface MoneyDialog {
    MoneyDialog define(Money money);
    Money get();
}
