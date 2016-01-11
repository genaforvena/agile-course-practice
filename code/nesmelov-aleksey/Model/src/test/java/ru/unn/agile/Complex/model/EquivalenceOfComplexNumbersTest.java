package ru.unn.agile.Complex.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class EquivalenceOfComplexNumbersTest {
    private Complex first;
    private Complex second;

    @Test
    public void canCheckThatNumbersAreEqual() {
        first = new Complex(3, 5);
        second = new Complex(3, 5);

        assertTrue(first.equals(second));
    }

    @Test
    public void canCheckThatNumbersWithDifferentRealPartAreNotEqual() {
        first = new Complex(3, 5);
        second = new Complex(5, 5);

        assertFalse(first.equals(second));
    }

    @Test
    public void canCheckThatNumbersWithDifferentImaginaryPartAreNotEqual() {
        first = new Complex(5, 8);
        second = new Complex(5, 5);

        assertFalse(first.equals(second));
    }

    @Test
    public void canCheckThatEqualsNumbersHaveEqualsHashCode() {
        first = new Complex(5, 8);
        second = new Complex(5, 8);

        assertEquals(first.hashCode(), second.hashCode());
    }
}
