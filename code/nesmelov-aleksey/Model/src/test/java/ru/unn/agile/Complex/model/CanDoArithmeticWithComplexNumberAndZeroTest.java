package ru.unn.agile.Complex.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CanDoArithmeticWithComplexNumberAndZeroTest {
    private Complex first;
    private Complex second;

    @Before
    public void setUpComplexNumberAndZero() {
        first = new Complex(2, 3);
        second = new Complex(0, 0);
    }

    @Test
    public void canAddComplexNumberWitZero() {
        Complex result = first.add(second);

        assertEquals(result, new Complex(2, 3));
    }

    @Test
    public void canSubtractComplexNumberFromZero() {
        Complex result = second.subtract(first);

        assertEquals(result, new Complex(-2, -3));
    }

    @Test
    public void canMultiplyComplexNumberWithZero() {
        Complex result = first.multiply(second);

        assertEquals(result, new Complex(0, 0));
    }

    @Test(expected = Exception.class)
    public void canNotDivideComplexNumbersToZero() {
        Complex result = first.divide(second);
    }
}
