package ru.unn.agile.Complex.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CanDoArithmeticWithPositiveComplexNumbersTest {
    private Complex first;
    private Complex second;

    @Before
    public void setUpPositiveComplexNumbers() {
        first = new Complex(2, 3);
        second = new Complex(1, 1);
    }

    @Test
    public void canAddComplexNumbersWithPositiveParts() {
        Complex result = first.add(second);

        assertEquals(result, new Complex(3, 4));
    }

    @Test
    public void canSubtractComplexNumbersWithPositiveParts() {
        Complex result = first.subtract(second);

        assertEquals(result, new Complex(1, 2));
    }

    @Test
    public void canMultiplyComplexNumbersWithPositiveParts() {
        Complex result = first.multiply(second);

        assertEquals(result, new Complex(-1, 5));
    }

    @Test
    public void canDivideComplexNumbersWithPositiveParts() {
        Complex result = first.divide(second);

        assertEquals(result, new Complex(2.5, 0.5));
    }
}
