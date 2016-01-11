package ru.unn.agile.NewtonMethod;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CanCalculateValueOfFunction {
    private Function function;
    private final String testFunc;
    private final double testPoint;
    private final double expectedValue;
    private double delta = 0.000001;

    public CanCalculateValueOfFunction(final String testFunc,
                                       final double testPoint,
                                       final double expectedValue) {
        this.testFunc = testFunc;
        this.testPoint = testPoint;
        this.expectedValue = expectedValue;
    }

    @Parameterized.Parameters
    public static List<Object[]> testDataFunc() {
        return Arrays.asList(new Object[][]{
                {"3=", 0.0, 3},
                {"3*x+5=", 1.0, 8.0},
                {"3+x=", 4.0, 7.0},
                {"3*x=", 4.0, 12.0},
                {"x*x*x+3*x*x-2*x-5=", 1.0, -3.0},
                {"5/x=", 0.00000000001, 5.0E11},
                {"x/3=", 10.0, 3.333333}
        });
    }

    @Before
    public void initializeFunction() {
        function = new Function(testFunc);
    }

    @Test
    public void canCalculateFunctionValue() {
        double calculatedValue = function.calculate(testPoint);
        assertEquals(expectedValue, calculatedValue, delta);
    }
}
