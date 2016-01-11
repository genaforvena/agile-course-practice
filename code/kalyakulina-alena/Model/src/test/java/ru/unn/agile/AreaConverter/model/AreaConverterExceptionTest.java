package ru.unn.agile.AreaConverter.model;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class AreaConverterExceptionTest {

    private final AreaMeasure fromMeasure;
    private final AreaMeasure toMeasure;
    private final double inputArea;

    private AreaConverter converter;

    public AreaConverterExceptionTest(final AreaMeasure fromMeasure,
                                      final AreaMeasure toMeasure,
                                      final double inputArea) {
        this.fromMeasure = fromMeasure;
        this.toMeasure = toMeasure;
        this.inputArea = inputArea;
    }

    @Parameterized.Parameters
    public static Collection measures() {
        return Arrays.asList(new Object[][] {
                {AreaMeasure.HECTARE, AreaMeasure.SQUARE_METER, -1.0},
                {AreaMeasure.SQUARE_KILOMETER, AreaMeasure.SQUARE_METER, Double.MAX_VALUE}
        });
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsExceptionWithWrongInput() {
        converter = new AreaConverter(fromMeasure, toMeasure);

        final double resultArea = converter.convert(inputArea);
    }
}
