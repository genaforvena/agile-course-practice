package ru.unn.agile.arabicroman.model;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class NumeralConverterParametrizedTest {
    private int arabicNumber;
    private String romanNumber;

    public NumeralConverterParametrizedTest(final int arabicNumber, final String romanNumber) {
        this.arabicNumber = arabicNumber;
        this.romanNumber = romanNumber;
    }

@Parameterized.Parameters
    public static Collection testedNumbers() {
        return Arrays.asList(new Object[][] {
                {19, "XIX"},
                {49, "XLIX"},
                {99, "XCIX"},
                {499, "CDXCIX"}
        });
    }

    @Test
    public void canConvertGivenNumbersToRoman() {
        assertEquals(romanNumber, NumeralConverter.convert(arabicNumber));
    }

    @Test
    public void canConvertGivenNumbersToArabic() {
        assertEquals(arabicNumber, NumeralConverter.convert(romanNumber));
    }
}


