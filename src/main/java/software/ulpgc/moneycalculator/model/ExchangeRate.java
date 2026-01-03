package software.ulpgc.moneycalculator.model;

import java.time.LocalDate;
import java.util.Objects;

public class ExchangeRate {
    private final Currency from;
    private final Currency to;
    private final LocalDate date;
    private final double rate;

    public ExchangeRate(Currency from, Currency to, LocalDate date, double rate) {
        this.from = Objects.requireNonNull(from, "From currency cannot be null");
        this.to = Objects.requireNonNull(to, "To currency cannot be null");
        this.date = Objects.requireNonNull(date, "Date cannot be null");
        this.rate = rate;
    }

    public Money convert(Money money) {
        if (!money.getCurrency().equals(from)) {
            throw new IllegalArgumentException("Money currency does not match the exchange rate source currency");
        }
        double resultAmount = money.getAmount() * rate;
        return new Money(resultAmount, to);
    }

    public Currency getFrom() {
        return from;
    }

    public Currency getTo() {
        return to;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return String.format("1 %s = %.4f %s (%s)", from.getIsoCode(), rate, to.getIsoCode(), date);
    }
}
