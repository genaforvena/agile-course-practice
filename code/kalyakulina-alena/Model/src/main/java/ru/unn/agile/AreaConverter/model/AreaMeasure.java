package ru.unn.agile.AreaConverter.model;

public enum AreaMeasure {
    SQUARE_METER("Square Meter", 1.0),
    SQUARE_KILOMETER("Square Kilometer", 1000000.0),
    ARE("Are", 100.0),
    HECTARE("Hectare", 10000.0);

    private final String name;
    private final double coefficient;

    AreaMeasure(final String name, final double coefficient) {
        this.name = name;
        this.coefficient = coefficient;
    }

    @Override
    public String toString() {
        return name;
    }

    public double getCoefficient() {
        return coefficient;
    }
}
