package software.ulpgc.moneycalculator.application;

import software.ulpgc.moneycalculator.control.Command;
import software.ulpgc.moneycalculator.control.ExchangeMoneyCommand;
import software.ulpgc.moneycalculator.io.CurrencyLoader;
import software.ulpgc.moneycalculator.io.ExchangeRateLoader;
import software.ulpgc.moneycalculator.io.FileCurrencyLoader;
import software.ulpgc.moneycalculator.io.FixerExchangeRateLoader;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.swing.MainFrame;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();

        CurrencyLoader currencyLoader = new FileCurrencyLoader("/currencies.json");
        List<Currency> currencies = currencyLoader.load();

        mainFrame.getCurrencyDialog().define(currencies);
        mainFrame.getSwingMoneyDialog().define(currencies);

        ExchangeRateLoader exchangeRateLoader = new FixerExchangeRateLoader();

        Command exchangeCommand = new ExchangeMoneyCommand(
                mainFrame.getMoneyDialog(),
                mainFrame.getCurrencyDialog(),
                exchangeRateLoader,
                mainFrame.getMoneyDisplay()
        );

        mainFrame.add("exchange", exchangeCommand);

        mainFrame.setVisible(true);
    }
}
