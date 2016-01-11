package ru.unn.agile.HexBinOctCalculator.Model;

public enum NumeralSystem {
    HEX(16),
    BIN(2),
    OCT(8);

    private final int value;

    NumeralSystem(final int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }
}
