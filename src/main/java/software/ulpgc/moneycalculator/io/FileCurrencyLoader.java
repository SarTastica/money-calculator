package software.ulpgc.moneycalculator.io;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import software.ulpgc.moneycalculator.model.Currency;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FileCurrencyLoader implements CurrencyLoader {
    private final String filePath;

    public FileCurrencyLoader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Currency> load() {
        try (InputStream is = FileCurrencyLoader.class.getResourceAsStream(filePath)) {
            if (is == null) return Collections.emptyList();
            return loadCurrencies(new String(is.readAllBytes()));
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    private List<Currency> loadCurrencies(String json) {
        List<Currency> list = new ArrayList<>();
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
        Map<String, Object> symbols = new Gson().fromJson(jsonObject.get("symbols"), Map.class);

        for (String symbol : symbols.keySet()) {
            list.add(new Currency(symbol, (String) symbols.get(symbol), ""));
        }
        return list;
    }
}
