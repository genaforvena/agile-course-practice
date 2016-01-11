package ru.unn.agile.Metrics.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class DistanceCalculatorCorrectnessTest {

    private float delta = 0.0001f;

    private DistanceCalculator distanceCalculator;

    static class CalculatorArguments {
        private float[] firstVec;
        private float[] secondVec;
        private Metric metric;

        public CalculatorArguments(final float[] firstVec,
                                   final float[] secondVec,
                                   final Metric metric) {
            this.firstVec = firstVec;
            this.secondVec = secondVec;
            this.metric = metric;
        }
    }

    private float expectedValue;
    private CalculatorArguments testData;

    public DistanceCalculatorCorrectnessTest(final float expectedValue,
                                             final CalculatorArguments testData) {
        this.expectedValue = expectedValue;
        this.testData = testData;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> generateData() {
        return Arrays.asList(new Object[][]{
                new Object[]{5.0f, new CalculatorArguments(new float[]{4.1f, -5.2f, 6.3f, -7.4f},
                        new float[] {3.1f, -9.2f, 8.3f, -2.4f}, Metric.RHO_INF)},
                new Object[]{12.0f, new CalculatorArguments(new float[]{4.1f, -5.2f, 6.3f, -7.4f},
                        new float[]{3.1f, -9.2f, 8.3f, -2.4f}, Metric.RHO_ONE)},
                new Object[]{6.0f, new CalculatorArguments(new float[]{-4.1f, 6.3f, -7.4f, 0.5f},
                        new float[]{-3.1f, 9.3f, -2.4f, 1.5f}, Metric.RHO_TWO)},
                new Object[]{4.0f, new CalculatorArguments(
                        new float[]{-4.1f, 6.3f, -7.4f, 5.5f, -1.6f},
                        new float[]{-3.1f, 8.3f, -4.4f, 2.5f, -0.6f}, Metric.RHO_THREE)},
                new Object[]{3.0f, new CalculatorArguments(
                        new float[]{-4.1f, 6.3f, -7.4f, 9.5f, -1.6f, 0.7f},
                        new float[]{-5.1f, 4.3f, -5.4f, 7.5f, -3.6f, 2.7f}, Metric.RHO_FOUR)}
        });
    }

    @Test
    public void canCalculateDistance() {
        distanceCalculator = new DistanceCalculator();
        float result = distanceCalculator.calculateDistance(testData.firstVec, testData.secondVec,
                testData.metric);
        assertEquals(expectedValue, result, delta);
    }
}
