package software.ulpgc.moneycalculator.io;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.Map;

public class FixerExchangeRateLoader implements ExchangeRateLoader {

    @Override
    public ExchangeRate load(Currency from, Currency to) {
        try {
            String json = loadJson();
            return toExchangeRate(json, from, to);
        } catch (IOException e) {
            throw new RuntimeException("No se pudieron cargar las tasas de cambio", e);
        }
    }

    private ExchangeRate toExchangeRate(String json, Currency from, Currency to) {
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
        JsonObject rates = jsonObject.getAsJsonObject("rates");
        LocalDate date = LocalDate.parse(jsonObject.get("date").getAsString());

        double rateFrom = getRateValue(from.getIsoCode(), rates);
        double rateTo = getRateValue(to.getIsoCode(), rates);

        return new ExchangeRate(from, to, date, rateTo / rateFrom);
    }

    private double getRateValue(String isoCode, JsonObject rates) {
        if (isoCode.equals("EUR")) {
            return 1.0;
        }
        if (rates.has(isoCode)) {
            return rates.get(isoCode).getAsDouble();
        }
        throw new IllegalArgumentException("La API no tiene datos para la moneda: " + isoCode);
    }

    private String loadJson() throws IOException {
        java.net.URL url = new java.net.URL("https://api.frankfurter.app/latest?from=EUR");

        try (InputStream is = url.openStream()) {
            return new String(is.readAllBytes());
        }
    }
}
