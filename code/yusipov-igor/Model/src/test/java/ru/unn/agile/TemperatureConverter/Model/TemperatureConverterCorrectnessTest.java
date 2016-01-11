package ru.unn.agile.TemperatureConverter.model;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TemperatureConverterCorrectnessTest {

    private static final double DELTA = 1e-16;
    private TemperatureConverter converter;

    private TemperatureScaleName scale;
    private double temperature;
    private double temperatureConverted;

    public TemperatureConverterCorrectnessTest(final TemperatureScaleName scale,
                                               final double temperature,
                                               final double temperatureConverted) {
        this.scale = scale;
        this.temperature = temperature;
        this.temperatureConverted = temperatureConverted;
    }

    @Parameterized.Parameters
    public static Collection temperatureScales() {
        return Arrays.asList(new Object[][] {
                {TemperatureScaleName.FAHRENHEIT, 0.0, 32.0},
                {TemperatureScaleName.FAHRENHEIT, 1.0, 33.8},
                {TemperatureScaleName.FAHRENHEIT, -1.0, 30.2},
                {TemperatureScaleName.KELVIN, 0.0, 273.0},
                {TemperatureScaleName.KELVIN, 1.0, 274.0},
                {TemperatureScaleName.KELVIN, -1.0, 272.0},
                {TemperatureScaleName.NEWTON, 0.0, 0.0},
                {TemperatureScaleName.NEWTON, 1.0, 0.33},
                {TemperatureScaleName.NEWTON, -1.0, -0.33}
        });
    }

    @Test
    public void isCorrectConvertSimpleNumbers() {
        converter = new TemperatureConverter(scale);

        double temperatureInOtherScale = converter.convert(temperature);

        assertEquals(temperatureInOtherScale, temperatureConverted, DELTA);
    }
}
