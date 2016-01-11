package ru.unn.agile.IntegrationMethods.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class CosFunctionTest {
    private final double delta = 0.001;

    @Test
    public void canGetValue() {
        CosFunction function = new CosFunction();
        double x = 1.5;
        double analyticalFunctionValue = Math.cos(x);

        assertEquals(analyticalFunctionValue, function.getValue(x), delta);
    }
}
