package software.ulpgc.moneycalculator.io;

import org.junit.jupiter.api.Test;
import software.ulpgc.moneycalculator.model.Currency;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileCurrencyLoaderTest {

    @Test
    public void should_load_currencies_from_file() {
        FileCurrencyLoader loader = new FileCurrencyLoader("/currencies.json");

        List<Currency> currencies = loader.load();

        assertNotNull(currencies);
        assertFalse(currencies.isEmpty(), "La lista de monedas no debería estar vacía");

        boolean containsEuro = currencies.stream()
                .anyMatch(c -> c.getIsoCode().equals("EUR"));

        assertTrue(containsEuro, "La lista debería contener  el Euro (EUR)");
    }

    @Test
    public void should_return_empty_list_if_file_not_found() {
        FileCurrencyLoader loader = new FileCurrencyLoader("/archivo_que_no_existe.json");

        List<Currency> currencies = loader.load();

        assertNotNull(currencies);
        assertTrue(currencies.isEmpty());
    }
}
