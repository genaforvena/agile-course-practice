package ru.unn.agile.Metrics.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class DistanceCalculatorExceptionTest {

    private final float delta = 0.0001f;

    private DistanceCalculator distanceCalculator;

    private Metric metric;

    public DistanceCalculatorExceptionTest(final Metric metric) {
        this.metric = metric;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> generateData() {
        return Arrays.asList(new Object[][]{
                new Object[]{Metric.RHO_INF},
                new Object[]{Metric.RHO_ONE},
                new Object[]{Metric.RHO_TWO},
                new Object[]{Metric.RHO_THREE},
                new Object[]{Metric.RHO_FOUR}
        });
    }

    @Before
    public void setUp() {
        distanceCalculator = new DistanceCalculator();
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullThrows() {
        float[] firstVector = null;
        float[] secondVector = {1, -2, 3};
        float result = distanceCalculator.calculateDistance(firstVector, secondVector, metric);
    }

    @Test(expected = IllegalArgumentException.class)
    public void vectorsWithDifferentSizesThrows() {
        float[] firstVector = {3, -4, 5};
        float[] secondVector = {-6, 7, -8, 9};
        float result = distanceCalculator.calculateDistance(firstVector, secondVector, metric);
    }

    @Test
    public void sameVectorsGivesZero() {
        float[] someVector = {1, -2 , 3};
        float result = distanceCalculator.calculateDistance(someVector, someVector, metric);
        assertEquals(0.0f, result, delta);
    }
}
