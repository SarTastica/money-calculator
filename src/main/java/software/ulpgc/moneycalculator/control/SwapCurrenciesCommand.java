package software.ulpgc.moneycalculator.control;

import software.ulpgc.moneycalculator.ui.CurrencyDialog;
import software.ulpgc.moneycalculator.ui.MoneyDialog;

public class SwapCurrenciesCommand implements Command {
    private final MoneyDialog moneyDialog;
    private final CurrencyDialog currencyDialog;

    public SwapCurrenciesCommand(MoneyDialog moneyDialog, CurrencyDialog currencyDialog) {
        this.moneyDialog = moneyDialog;
        this.currencyDialog = currencyDialog;
    }

    @Override
    public void execute() {
        var fromCurrency = moneyDialog.get().getCurrency();
        var toCurrency = currencyDialog.get();

        moneyDialog.setCurrency(toCurrency);
        currencyDialog.setCurrency(fromCurrency);
    }
}