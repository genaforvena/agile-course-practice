package ru.unn.agile.AreaConverter.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AreaMeasureTest {

    private final AreaMeasure measure;
    private final String txtMeasure;

    public AreaMeasureTest(final AreaMeasure measure,
                           final String txtMeasure) {
        this.measure = measure;
        this.txtMeasure = txtMeasure;
    }

    @Parameterized.Parameters
    public static Collection measures() {
        return Arrays.asList(new Object[][] {
                {AreaMeasure.SQUARE_METER, "Square Meter"},
                {AreaMeasure.SQUARE_KILOMETER, "Square Kilometer"},
                {AreaMeasure.ARE, "Are"},
                {AreaMeasure.HECTARE, "Hectare"},
        });
    }

    @Test
    public void canConvertMeasureNameToString() {
        assertEquals(measure.toString(), txtMeasure);
    }
}
