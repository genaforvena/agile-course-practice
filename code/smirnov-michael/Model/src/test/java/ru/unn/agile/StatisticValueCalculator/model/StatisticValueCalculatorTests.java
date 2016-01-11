package ru.unn.agile.StatisticValueCalculator.model;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class StatisticValueCalculatorTests {

    @RunWith(Parameterized.class)
    public static class WhenStatisticDataIsNull {
        private final IStatisticValueCalculator calculator;

        @Parameterized.Parameters
        public static Collection<Object[]> getDataForTesting() {
            return Arrays.asList(new Object[][]{
                    {new MeanCalculator()},
                    {new VarianceCalculator()},
                    {new ProbabilityOfEventCalculator(1.2)},
                    {new CentralMomentCalculator(2)}});
        }

        public WhenStatisticDataIsNull(final IStatisticValueCalculator calculator) {
            this.calculator = calculator;
        }

        @Test(expected = NullPointerException.class)
        public void throwsWhenTryToCalculateStatisticValueOnNullData() {
            calculator.calculate(null);
        }
    }

    public static class WhenMomentOrderIsIncorrect {
        @Test(expected = InvalidParameterException.class)
        public void throwsWhenInstantiateRawMomentOfZeroOrder() {
            new RawMomentCalculator(0);
        }

        @Test(expected = InvalidParameterException.class)
        public void throwsWhenInstantiateRawMomentWithNegativeOrder() {
            new RawMomentCalculator(-5);
        }

        @Test(expected = InvalidParameterException.class)
        public void throwsWhenInstantiateCentralMomentWithNegativeOrder() {
            new CentralMomentCalculator(-3);
        }

        @Test(expected = InvalidParameterException.class)
        public void throwsWhenInstantiateCentralMomentOfZeroOrder() {
            new CentralMomentCalculator(0);
        }
    }

    @RunWith(Parameterized.class)
    public static class WhenCalculateStatisticValues {
        private IStatisticValueCalculator calculator;
        private Collection<Double> currentTestingData;
        private double expectedResult;

        @Parameterized.Parameters
        public static Collection<Object[]> getDataForTesting() {
            return Arrays.asList(new Object[][]{
                    {new MeanCalculator(), new ArrayList<Double>(), 0.0},
                    {new MeanCalculator(), getMonotoneArray(), 49.5},
                    {new VarianceCalculator(), new ArrayList<>(Arrays.asList(5., 5., 5.)), 0.0},
                    {new VarianceCalculator(), getArrayOfOneCosPeriod(), 0.5},
                    {new ProbabilityOfEventCalculator(-1.0),
                            new ArrayList<>(Arrays.asList(-1., -1., 5., 8., 10.)),
                            2.0 / 5.0},
                    {new ProbabilityOfEventCalculator(2.25),
                            new ArrayList<>(Arrays.asList(1., 2.25, 1., 2.25, 2.25, 2.25)),
                            4.0 / 6.0},
                    {new MomentCalculator(1, 0.0), new ArrayList<>(Arrays.asList(1., 1., 1.)), 1.0},
                    {new RawMomentCalculator(1), getMonotoneArray(), 49.5},
                    {new RawMomentCalculator(5), new ArrayList<Double>(), 0.0},
                    {new CentralMomentCalculator(3), new ArrayList<>(Arrays.asList(5., 5.)), 0.0},
                    {new CentralMomentCalculator(2), new ArrayList<>(Arrays.asList(1., 2.)), 0.25}
            });
        }

        public WhenCalculateStatisticValues(
                final IStatisticValueCalculator calculator,
                final ArrayList<Double> testingArray,
                final double expectedResult) {

            currentTestingData = testingArray;
            this.calculator = calculator;
            this.expectedResult = expectedResult;
        }

        @Test
        public void calculatedStatisticValueEqualsToExpectedResult() {
            assertEquals(calculator.calculate(currentTestingData), expectedResult, 1e-3);
        }

        private static ArrayList<Double> getMonotoneArray() {
            ArrayList<Double> array = new ArrayList<>();
            for (Integer i = 0; i < 100; i++) {
                array.add(i.doubleValue());
            }
            return array;
        }


        private static ArrayList<Double> getArrayOfOneCosPeriod() {
            ArrayList<Double> array = new ArrayList<>();
            int countElements = 1000;

            for (int i = 0; i < countElements; i++) {
                array.add(Math.cos(i * 2 * Math.PI / countElements));
            }
            return array;
        }
    }
}
