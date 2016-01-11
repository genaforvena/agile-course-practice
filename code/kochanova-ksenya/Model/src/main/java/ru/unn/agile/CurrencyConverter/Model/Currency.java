package ru.unn.agile.CurrencyConverter.Model;

import java.util.Objects;

public class Currency {
    private double value;
    private UnitCurrency unit;

    public Currency(final String value, final UnitCurrency unit) {
        this.value = Double.parseDouble(value);
        this.unit = unit;
    }
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Currency)) {
            return false;
        }

        Currency currency = (Currency) o;
        return Objects.equals(value, currency.value)
                && Objects.equals(unit, currency.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    public Currency(final double value, final UnitCurrency unit) {
        this.value = value;
        this.unit = unit;
    }

    public void setValue(final double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    public void setUnit(final UnitCurrency unit) {
        this.unit = unit;
    }

    public UnitCurrency getUnit() {
        return unit;
    }

}
