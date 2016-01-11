package ru.unn.agile.CurrencyConverter.Model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)

public class CurrencyConverterTest {

    @Parameterized.Parameters
    public static Collection testValue() {
        return Arrays.asList(new Object[][]{
                {0, UnitCurrency.DOLLAR, 0.0, UnitCurrency.DOLLAR},
                {100, UnitCurrency.RUBLE, 1.6, UnitCurrency.DOLLAR},
                {100, UnitCurrency.RUBLE, 1.38, UnitCurrency.EURO},
                {100, UnitCurrency.RUBLE, 1.01, UnitCurrency.POUND},
                {100, UnitCurrency.DOLLAR, 86.21, UnitCurrency.EURO},
                {100, UnitCurrency.DOLLAR, 6250.00, UnitCurrency.RUBLE},
                {100, UnitCurrency.DOLLAR, 62.89, UnitCurrency.POUND},
                {100, UnitCurrency.EURO, 116.00, UnitCurrency.DOLLAR},
                {100, UnitCurrency.EURO, 7250.00, UnitCurrency.RUBLE},
                {100, UnitCurrency.EURO, 72.96, UnitCurrency.POUND},
                {100, UnitCurrency.POUND, 137.07, UnitCurrency.EURO},
                {100, UnitCurrency.POUND, 9937.50, UnitCurrency.RUBLE},
                {100, UnitCurrency.POUND, 159.00, UnitCurrency.DOLLAR},
        });
        }
    private double inputValue, outputValue;
    private UnitCurrency inputUnit, outputUnit;
    public CurrencyConverterTest(final double inputValue, final UnitCurrency inputUnit,
                                  final double outputValue, final UnitCurrency outputUnit) {
        this.inputValue = inputValue;
        this.outputValue = outputValue;
        this.inputUnit = inputUnit;
        this.outputUnit = outputUnit;
    }
    @Test
    public void canConvertInputUnitToOutputUnit() {
    Currency expectedCurrency = new Currency(outputValue, outputUnit);
    Currency currencyBeforeConvert = new Currency(inputValue, inputUnit);
    Currency currencyAfterConvert = inputUnit.convertCurrency(currencyBeforeConvert,
            outputUnit);
    assertEquals(expectedCurrency, currencyAfterConvert);

    }
    @Test(expected = IllegalArgumentException.class)
    public void cannotConvertNegativeValue() {
        boolean thrown = false;
        Currency currencyBeforeConvert = new Currency(-100, UnitCurrency.DOLLAR);
        Currency currencyAfterConvert = UnitCurrency.DOLLAR.convertCurrency(
                    currencyBeforeConvert, UnitCurrency.DOLLAR);
    }

    @Test(expected = NullPointerException.class)
    public void cannotConvertNull() {
        Currency currencyBeforeConvert = null;
        Currency currencyAfterConvert = UnitCurrency.DOLLAR.convertCurrency(
                    currencyBeforeConvert, UnitCurrency.DOLLAR);
    }
}
