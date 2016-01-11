package ru.unn.agile.AreaConverter.model;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class AreaConverterTest {

    private static final double DELTA = 1e-10;

    private final AreaMeasure fromMeasure;
    private final AreaMeasure toMeasure;
    private final double inputArea;
    private final double outputArea;

    private AreaConverter converter;

    public AreaConverterTest(final AreaMeasure fromMeasure,
                             final AreaMeasure toMeasure,
                             final double inputArea,
                             final double outputArea) {
        this.fromMeasure = fromMeasure;
        this.toMeasure = toMeasure;
        this.inputArea = inputArea;
        this.outputArea = outputArea;
    }

    @Parameterized.Parameters
    public static Collection measures() {
        return Arrays.asList(new Object[][] {
                {AreaMeasure.ARE, AreaMeasure.HECTARE, 0.0, 0.0},
                {AreaMeasure.SQUARE_METER, AreaMeasure.SQUARE_KILOMETER, 152.0, 0.000152},
                {AreaMeasure.SQUARE_METER, AreaMeasure.ARE, 10.0, 0.1},
                {AreaMeasure.SQUARE_METER, AreaMeasure.HECTARE, 0.5, 0.00005},
                {AreaMeasure.SQUARE_KILOMETER, AreaMeasure.SQUARE_METER, 7.5, 7500000.0},
                {AreaMeasure.SQUARE_KILOMETER, AreaMeasure.ARE, 25.0, 250000.0},
                {AreaMeasure.SQUARE_KILOMETER, AreaMeasure.HECTARE, 0.01, 1.0},
                {AreaMeasure.ARE, AreaMeasure.SQUARE_METER, 500.0, 50000.0},
                {AreaMeasure.ARE, AreaMeasure.SQUARE_KILOMETER, 6.4, 0.00064},
                {AreaMeasure.ARE, AreaMeasure.HECTARE, 12.3, 0.123},
                {AreaMeasure.HECTARE, AreaMeasure.SQUARE_METER, 0.2, 2000.0},
                {AreaMeasure.HECTARE, AreaMeasure.SQUARE_KILOMETER, 78.0, 0.78},
                {AreaMeasure.HECTARE, AreaMeasure.ARE, 0.01, 1.0}
        });
    }

    @Test
    public void isCorrectConverting() {
        converter = new AreaConverter(fromMeasure, toMeasure);

        final double resultArea = converter.convert(inputArea);

        assertEquals(resultArea, outputArea, DELTA);
    }
}
