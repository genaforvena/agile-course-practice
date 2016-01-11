package ru.unn.agile.TemperatureConverter.model;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class TemperatureConverterExceptionThrowTest {

    private TemperatureScaleName scale;
    private double temperature;

    private TemperatureConverter converter;

    public TemperatureConverterExceptionThrowTest(final TemperatureScaleName scale,
                                                  final double temperature) {
        this.scale = scale;
        this.temperature = temperature;
    }

    @Parameterized.Parameters
    public static Collection temperatureScales() {
        return Arrays.asList(new Object[][]{
                {TemperatureScaleName.FAHRENHEIT, Double.MAX_VALUE},
                {TemperatureScaleName.KELVIN, Double.MAX_VALUE},
                {TemperatureScaleName.FAHRENHEIT, -274.0},
                {TemperatureScaleName.KELVIN, -274.0},
                {TemperatureScaleName.NEWTON, -274.0}
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void canConvertCelsiusToOtherScaleLargeNumber() {
        converter = new TemperatureConverter(scale);

        double temperatureInOtherScale = converter.convert(temperature);
    }
}


