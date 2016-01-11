package ru.unn.agile.LengthConvertor.Model;

public enum LengthUnit {
    INCH(0.0253999998),
    FOOT(0.3048),
    YARD(0.914399999),
    MILE(1609.34401),
    METER(1),
    KMETER(1000),
    CMETER(0.01);

    private final double multiplierMeter;

    LengthUnit(final double multiplierMeter) {
        this.multiplierMeter = multiplierMeter;
    }

    public double convert(final double value, final LengthUnit unitInput) {
        if (value >= 0 && value < Double.MAX_VALUE) {
            double result = unitInput.multiplierMeter
                    / multiplierMeter * value;
            return result;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String convert(final String value, final LengthUnit unitInput) {
        return Double.toString(convert(Double.parseDouble(value), unitInput));
    }

    public double getMultiplierMeter() {
        return multiplierMeter;
    }
}
