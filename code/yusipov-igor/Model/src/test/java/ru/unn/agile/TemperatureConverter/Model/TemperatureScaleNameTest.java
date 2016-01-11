package ru.unn.agile.TemperatureConverter.model;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TemperatureScaleNameTest {

    private TemperatureScaleName scale;
    private String scaleName;

    public TemperatureScaleNameTest(final TemperatureScaleName scale,
                                    final String scaleName) {
        this.scale = scale;
        this.scaleName = scaleName;
    }

    @Parameterized.Parameters
    public static Collection temperatureScales() {
        return Arrays.asList(new Object[][] {
                {TemperatureScaleName.FAHRENHEIT, "Fahrenheit"},
                {TemperatureScaleName.KELVIN, "Kelvin"},
                {TemperatureScaleName.NEWTON, "Newton"},
        });
    }

    @Test
    public void isCorrectScaleName() {
        assertEquals(scale.toString(), scaleName);
    }
}
