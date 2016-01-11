package ru.unn.agile.HexBinOctCalculator.Model;

public class Number {
    private final String value;
    private final NumeralSystem system;

    public Number(final String value, final NumeralSystem system) {
        this.value = value;
        this.system = system;
    }

    public String getValue() {
        return value;
    }

    public NumeralSystem getSystem() {
        return system;
    }
}
