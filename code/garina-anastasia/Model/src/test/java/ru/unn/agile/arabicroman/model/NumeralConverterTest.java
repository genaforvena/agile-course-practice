package ru.unn.agile.arabicroman.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class NumeralConverterTest {

    @Test
    public void canConvertZeroToEmptyString() {
        assertEquals("", NumeralConverter.convert(0));
    }

    @Test
    public void canConvertEmptyStringToZero() {
        assertEquals(0, NumeralConverter.convert(""));
    }

    @Test
    public void canConvertOneToI() {
        assertEquals("I", NumeralConverter.convert(1));
    }

    @Test
    public void canConvertThirtyToXXX() {
        assertEquals("XXX", NumeralConverter.convert(30));
    }

    @Test
    public void canConvertTwelveToXII() {
        assertEquals("XII", NumeralConverter.convert(12));
    }

    @Test
    public void canConvertEightToVIII() {
        assertEquals("VIII", NumeralConverter.convert(8));
    }

    @Test
    public void canConvertFirtyToL() {
        assertEquals("L", NumeralConverter.convert(50));
    }

    @Test
    public void canConvertNinetyToXC() {
        assertEquals("XC", NumeralConverter.convert(90));
    }

    @Test
    public void canConvert2014ToMMXIV() {
        assertEquals("MMXIV", NumeralConverter.convert(2014));
    }

    @Test
    public void canConvertIToOne() {
        assertEquals(1, NumeralConverter.convert("I"));
    }

    @Test
    public void canConvertMTo1000() {
        assertEquals(1000, NumeralConverter.convert("M"));
    }
    @Test
    public void canConvertCCCXXXIIITo333() {
        assertEquals(333, NumeralConverter.convert("CCCXXXIII"));
    }

    @Test
    public void canConvertIXTo9() {
        assertEquals(9, NumeralConverter.convert("IX"));
    }

    @Test
    public void canConvertCMXIVTo914() {
        assertEquals(914, NumeralConverter.convert("CMXIV"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwOnNumberBiggerThanMax() {
        NumeralConverter.convert(5000);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwOnNegativeNumber() {
        NumeralConverter.convert(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwOnInvalidSymbolInRomanNumber() {
        NumeralConverter.convert("KLN");
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwOnIncorrectOrderOfSymbolsInRomanNumber() {
        NumeralConverter.convert("IMC");
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwOnIncorrectWritingOfRomanNumber() {
        NumeralConverter.convert("VXVI");
    }

    @Test
    public void canConvertWithNullAsArgument() {
        assertEquals(0, NumeralConverter.convert(null));
    }

}
