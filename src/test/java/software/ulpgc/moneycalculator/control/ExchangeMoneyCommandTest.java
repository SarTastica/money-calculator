package software.ulpgc.moneycalculator.control;

import org.junit.jupiter.api.Test;
import software.ulpgc.moneycalculator.io.ExchangeRateLoader;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;
import software.ulpgc.moneycalculator.model.Money;
import software.ulpgc.moneycalculator.ui.CurrencyDialog;
import software.ulpgc.moneycalculator.ui.MoneyDialog;
import software.ulpgc.moneycalculator.ui.MoneyDisplay;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExchangeMoneyCommandTest {

    @Test
    public void should_exchange_money_correctly() {
        Currency eur = new Currency("EUR", "Euro", "€");
        Currency usd = new Currency("USD", "Dollar", "$");

        MoneyDialog moneyDialog = new MoneyDialog() {
            @Override
            public MoneyDialog define(Money money) { return this; }

            @Override
            public Money get() {
                return new Money(100, eur);
            }
        };

        CurrencyDialog currencyDialog = new CurrencyDialog() {
            @Override
            public CurrencyDialog define(List<Currency> currencies) { return this; }

            @Override
            public Currency get() {
                return usd;
            }
        };

        ExchangeRateLoader loader = (from, to) ->
                new ExchangeRate(from, to, LocalDate.now(), 1.5);

        final Money[] result = new Money[1];
        MoneyDisplay display = money -> result[0] = money;

        Command command = new ExchangeMoneyCommand(moneyDialog, currencyDialog, loader, display);

        command.execute();

        assertEquals(150.0, result[0].getAmount());
        assertEquals(usd, result[0].getCurrency());
    }
}
