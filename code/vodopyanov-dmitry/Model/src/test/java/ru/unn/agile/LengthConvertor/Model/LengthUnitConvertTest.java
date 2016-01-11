package ru.unn.agile.LengthConvertor.Model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Collection;
import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class LengthUnitConvertTest {
    private double value;
    private double convertedValue;
    private LengthUnit unitOutput;

    private final double delta = 0.001;

    public LengthUnitConvertTest(final double value, final double convertedValue,
                                 final LengthUnit unitOutput) {
        this.value = value;
        this.convertedValue = convertedValue;
        this.unitOutput = unitOutput;
    }

    @Parameters
    public static Collection<Object[]> meterToUnits() {
        return Arrays.asList(new Object[][]{
                {1, 39.3700, LengthUnit.INCH},
                {1, 3.2808, LengthUnit.FOOT},
                {1, 1.0939, LengthUnit.YARD},
                {1, 0.0006, LengthUnit.MILE},
                {1, 1, LengthUnit.METER},
                {1, 0.001, LengthUnit.KMETER},
                {1, 100, LengthUnit.CMETER},
        });
    }

    @Test
    public void canConvertOneMeterToAllUnits() {
        LengthUnit convertedUnit = unitOutput;
        double res = convertedUnit.convert(value, LengthUnit.METER);
        assertEquals(convertedValue, res, delta);
    }
}
