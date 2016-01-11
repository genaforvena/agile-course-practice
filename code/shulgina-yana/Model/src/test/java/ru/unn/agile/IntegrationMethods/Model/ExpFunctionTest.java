package ru.unn.agile.IntegrationMethods.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class ExpFunctionTest {
    private final double delta = 0.001;

    @Test
    public void canGetValue() {
        ExpFunction function = new ExpFunction();
        double x = 1.5;
        double analyticalFunctionValue = Math.exp(x);

        assertEquals(analyticalFunctionValue, function.getValue(x), delta);
    }
}
