package is2.model;

import java.util.Objects;

public class Currency {
    private final String isoCode;
    private final String name;
    private final String symbol;

    public Currency(String isoCode, String name, String symbol) {
        this.isoCode = Objects.requireNonNull(isoCode, "ISO Code cannot be null").toUpperCase();
        this.name = Objects.requireNonNull(name, "Name cannot be null");
        this.symbol = Objects.requireNonNull(symbol, "Symbol cannot be null");
    }

    public String getIsoCode() {
        return isoCode;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return name + " (" + isoCode + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(isoCode, currency.isoCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isoCode);
    }
}