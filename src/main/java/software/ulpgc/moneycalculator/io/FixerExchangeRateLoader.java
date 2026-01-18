package software.ulpgc.moneycalculator.io;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;

public class FixerExchangeRateLoader implements ExchangeRateLoader {

    @Override
    public ExchangeRate load(Currency from, Currency to) {
        try {
            String json = loadJson();
            return toExchangeRate(json, from, to);
        } catch (IOException e) {
            throw new RuntimeException("No se pudieron cargar las tasas de cambio.", e);
        }
    }

    private String loadJson() throws IOException {
        URL url = new URL("https://open.er-api.com/v6/latest/EUR");

        try (InputStream is = url.openStream()) {
            return new String(is.readAllBytes());
        } catch (IOException e) {
            System.out.println("Usando respaldo local por fallo de red.");
            try (InputStream is = getClass().getResourceAsStream("/exchangerates.json")) {
                if (is == null) throw new IOException("No backup file found");
                return new String(is.readAllBytes());
            }
        }
    }

    private ExchangeRate toExchangeRate(String json, Currency from, Currency to) {
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
        JsonObject rates = jsonObject.getAsJsonObject("rates");

        LocalDate date = LocalDate.now();

        double rateFrom = getRateValue(from.getIsoCode(), rates);
        double rateTo = getRateValue(to.getIsoCode(), rates);

        return new ExchangeRate(from, to, date, rateTo / rateFrom);
    }

    private double getRateValue(String isoCode, JsonObject rates) {
        if (isoCode.equals("EUR")) return 1.0;
        if (rates.has(isoCode)) return rates.get(isoCode).getAsDouble();
        throw new IllegalArgumentException("La API no tiene datos para: " + isoCode);
    }
}
