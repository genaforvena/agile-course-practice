package ru.unn.agile.IntegrationMethods.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class XFunctionTest {
    private final double delta = 0.001;

    @Test
    public void canGetValue() {
        XFunction function = new XFunction();
        double x = 1.5;
        double analyticalFunctionValue = 1.5;

        assertEquals(analyticalFunctionValue, function.getValue(x), delta);
    }
}
