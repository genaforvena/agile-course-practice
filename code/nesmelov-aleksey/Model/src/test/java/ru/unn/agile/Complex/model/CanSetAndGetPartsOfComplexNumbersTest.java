package ru.unn.agile.Complex.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CanSetAndGetPartsOfComplexNumbersTest {
    private final double epsil = 0.00000000000000001;
    private Complex complexNumber;

    @Before
    public void setUpComplexNumber() {
        complexNumber = new Complex(1, 4);
    }
    @Test
    public void canSetPositiveReal() {
        complexNumber.setReal(5);

        Assert.assertEquals(complexNumber, new Complex(5, 4));
    }

    @Test
    public void canSetNegativeReal() {
        complexNumber.setReal(-5);

        Assert.assertEquals(complexNumber, new Complex(-5, 4));
    }

    @Test
    public void canSetZeroReal() {
        complexNumber.setReal(0);

        Assert.assertEquals(complexNumber, new Complex(0, 4));
    }

    @Test
    public void canSetPositiveImaginary() {
        complexNumber.setImaginary(5);

        Assert.assertEquals(complexNumber, new Complex(1, 5));
    }

    @Test
    public void canSetZeroImaginary() {
        complexNumber.setImaginary(0);

        Assert.assertEquals(complexNumber, new Complex(1, 0));
    }

    @Test
    public void canGetReal() {
        Assert.assertEquals(1.0, complexNumber.getReal(), epsil);
    }

    @Test
    public void canGetImaginary() {
        Assert.assertEquals(4.0, complexNumber.getImaginary(), epsil);
    }
}
