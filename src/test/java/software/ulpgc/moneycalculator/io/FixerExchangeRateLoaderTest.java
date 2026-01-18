package software.ulpgc.moneycalculator.io;

import org.junit.jupiter.api.Test;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;

import static org.junit.jupiter.api.Assertions.*;

public class FixerExchangeRateLoaderTest {

    @Test
    public void should_return_exchange_rate() {
        FixerExchangeRateLoader loader = new FixerExchangeRateLoader();
        Currency eur = new Currency("EUR", "Euro", "€");
        Currency usd = new Currency("USD", "Dollar", "$");

        ExchangeRate rate = loader.load(eur, usd);

        assertNotNull(rate);
        assertEquals(eur, rate.getFrom());
        assertEquals(usd, rate.getTo());
        assertTrue(rate.getRate() > 0);

        System.out.println("Tasa cargada: " + rate.getRate());
    }

    @Test
    public void should_calculate_cross_rate() {
        FixerExchangeRateLoader loader = new FixerExchangeRateLoader();
        Currency usd = new Currency("USD", "Dollar", "$");
        Currency gbp = new Currency("GBP", "Pound", "£");

        ExchangeRate rate = loader.load(usd, gbp);

        assertNotNull(rate);
        assertTrue(rate.getRate() > 0);
    }
}
