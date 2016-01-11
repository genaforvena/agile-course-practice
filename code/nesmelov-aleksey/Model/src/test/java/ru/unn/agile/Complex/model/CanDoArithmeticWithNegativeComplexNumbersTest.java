package ru.unn.agile.Complex.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CanDoArithmeticWithNegativeComplexNumbersTest {
    private Complex first;
    private Complex second;

    @Before
    public void setUpNegativeComplexNumbers() {
        first = new Complex(-2, -3);
        second = new Complex(-1, -1);
    }

    @Test
    public void canAddComplexNumbersWithNegativeParts() {
        Complex result = first.add(second);

        assertEquals(result, new Complex(-3, -4));
    }

    @Test
    public void canSubtractComplexNumbersWithNegativeParts() {
        Complex result = first.subtract(second);

        assertEquals(result, new Complex(-1, -2));
    }

    @Test
    public void canMultiplyComplexNumbersWithNegativeParts() {
        Complex result = first.multiply(second);

        assertEquals(result, new Complex(-1, 5));
    }

    @Test
    public void canDivideComplexNumbersWithNegativeParts() {
        Complex result = first.divide(second);

        assertEquals(result, new Complex(2.5, 0.5));
    }
}
