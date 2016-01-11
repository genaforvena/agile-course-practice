package ru.unn.agile.Complex.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CanCreateComplexNumbersTest {
    private final double epsil = 0.00000000000000001;
    private Complex complexNumber;

    @Test
    public void canCreateComplexNumberWithTwoParameters() {
        complexNumber = new Complex(1, 4);

        assertNotNull(complexNumber);
    }

    @Test
    public void canSetInitialRealWithTwoParameters() {
        complexNumber = new Complex(1, 4);

        Assert.assertEquals(1.0, complexNumber.getReal(), epsil);
    }

    @Test
    public void canSetInitialImaginaryWithTwoParameters() {
        complexNumber = new Complex(1, 4);

        Assert.assertEquals(4.0, complexNumber.getImaginary(), epsil);
    }

    @Test
    public void canCreateComplexNumberWithOneParameter() {
        complexNumber = new Complex(1);

        assertNotNull(complexNumber);
    }

    @Test
    public void canInitialImaginaryAsZeroWhenOneParameter() {
        complexNumber = new Complex(1);

        Assert.assertEquals(0, complexNumber.getImaginary(), epsil);
    }

    @Test
    public void canCheckThatComplexNumberWithOneParameterIsEqualComplexNumberWithTwoParameters() {
        complexNumber = new Complex(1);

        Assert.assertEquals(complexNumber, new Complex(1, 0));
    }

    @Test
    public void canCreateComplexNumberWitoutParameters() {
        complexNumber = new Complex();

        assertNotNull(complexNumber);
    }

    @Test
    public void canCheckThatComplexNumberWithoutParametersIsZero() {
        complexNumber = new Complex();

        Assert.assertEquals(complexNumber, new Complex(0, 0));
    }

    @Test
    public void canCheckThatZeroNumberIsZero() {
        complexNumber = new Complex();

        assertTrue(complexNumber.isZero());
    }

    @Test
    public void canCheckThatNotZeroNumberIsNotZero() {
        complexNumber = new Complex(1, 4);

        assertFalse(complexNumber.isZero());
    }

    @Test
    public void canCheckThatStringIsCorrectWhenPositiveNumber() {
        complexNumber = new Complex(1, 4);

        assertEquals("1.0+4.0i", complexNumber.toString());
    }

    @Test
    public void canCheckThatStringIsCorrectWhenNegativeNumber() {
        complexNumber = new Complex(-1, -4);

        assertEquals("-1.0-4.0i", complexNumber.toString());
    }

    @Test
    public void canCheckThatStringIsCorrectWhenRealIsZero() {
        complexNumber = new Complex(0, 4);

        assertEquals("4.0i", complexNumber.toString());
    }

    @Test
    public void canCheckThatStringIsCorrectWhenImaginaryIsZero() {
        complexNumber = new Complex(1, 0);

        assertEquals("1.0", complexNumber.toString());
    }

    @Test
    public void canCheckThatStringIsCorrectWhenNumberIsZero() {
        complexNumber = new Complex(0, 0);

        assertEquals("0.0", complexNumber.toString());
    }
}
