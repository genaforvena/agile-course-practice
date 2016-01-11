package ru.unn.agile.LengthConvertor.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class LengthConvertorTest {
    private final double delta = 0.001;

    @Test
    public void areEqualResultsEqual() {
        double res1 = LengthUnit.FOOT.convert(3, LengthUnit.METER);
        double res2 = LengthUnit.FOOT.convert(3, LengthUnit.METER);
        assertEquals(res1, res2, delta);
    }

    @Test
    public void areResultsWithDifferentValuesNotEqual() {
        double res1 = LengthUnit.FOOT.convert(3, LengthUnit.METER);
        double res2 = LengthUnit.FOOT.convert(4, LengthUnit.METER);
        assertNotEquals(res1, res2, delta);
    }

    @Test
    public void areResultsWithDifferentOutputUnitNotEqual() {
        double res1 = LengthUnit.INCH.convert(3, LengthUnit.METER);
        double res2 = LengthUnit.FOOT.convert(3, LengthUnit.METER);
        assertNotEquals(res1, res2, delta);
    }

    @Test
    public void areValuesEqual() {
        double res = LengthUnit.INCH.convert(347.23, LengthUnit.INCH);
        assertEquals(347.23, res, delta);
    }

    @Test
    public void isMultiplierEqualToConstMultiplier() {
        double res = LengthUnit.INCH.getMultiplierMeter();
        assertEquals(0.0253999998, res, delta);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnNegativeValue() {
        LengthUnit.METER.convert(-1, LengthUnit.INCH);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnDoubleMaxValue() {
        LengthUnit.METER.convert(Double.MAX_VALUE, LengthUnit.INCH);
    }
}
