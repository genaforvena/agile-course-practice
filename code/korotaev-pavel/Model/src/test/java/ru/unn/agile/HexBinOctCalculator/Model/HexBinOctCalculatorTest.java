package ru.unn.agile.HexBinOctCalculator.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class HexBinOctCalculatorTest {
    @Test
    public void canAddOneBinOneHexNumbersAndConvertResultToOct() {
        Number firstNumber = new Number("101", NumeralSystem.BIN);
        Number secondNumber = new Number("2", NumeralSystem.HEX);

        Number result = HexBinOctCalculator.add(firstNumber, secondNumber, NumeralSystem.OCT);

        assertEquals("7", result.getValue());
    }

    @Test
    public void canSubtractTwoBinNumbers() {
        Number firstNumber = new Number("111", NumeralSystem.BIN);
        Number secondNumber = new Number("11", NumeralSystem.BIN);

        Number result = HexBinOctCalculator.subtract(firstNumber, secondNumber, NumeralSystem.BIN);

        assertEquals("100", result.getValue());
    }

    @Test
    public void canMultiplyTwoBinNumbers() {
        Number firstNumber = new Number("11", NumeralSystem.BIN);
        Number secondNumber = new Number("11", NumeralSystem.BIN);

        Number result = HexBinOctCalculator.multiply(firstNumber, secondNumber, NumeralSystem.BIN);

        assertEquals("1001", result.getValue());
    }

    @Test
    public void canDivideTwoBinNumbers() {
        Number firstNumber = new Number("1111", NumeralSystem.BIN);
        Number secondNumber = new Number("10", NumeralSystem.BIN);

        Number result = HexBinOctCalculator.divide(firstNumber, secondNumber, NumeralSystem.BIN);

        assertEquals("111", result.getValue());
    }

    @Test
    public void canAddOneHexOneOctNumbersAndConvertResultToBin() {
        Number firstNumber = new Number("ab", NumeralSystem.HEX);
        Number secondNumber = new Number("315", NumeralSystem.OCT);

        Number result = HexBinOctCalculator.add(firstNumber, secondNumber, NumeralSystem.BIN);

        assertEquals("101111000", result.getValue());
    }

    @Test
    public void canSubtractTwoHexNumbers() {
        Number firstNumber = new Number("cde", NumeralSystem.HEX);
        Number secondNumber = new Number("5", NumeralSystem.HEX);

        Number result = HexBinOctCalculator.subtract(firstNumber, secondNumber, NumeralSystem.HEX);

        assertEquals("cd9", result.getValue());
    }

    @Test
    public void canMultiplyTwoHexNumbers() {
        Number firstNumber = new Number("4", NumeralSystem.HEX);
        Number secondNumber = new Number("be", NumeralSystem.HEX);

        Number result = HexBinOctCalculator.multiply(firstNumber, secondNumber, NumeralSystem.HEX);

        assertEquals("2f8", result.getValue());
    }

    @Test
    public void canDivideTwoHexNumbers() {
        Number firstNumber = new Number("bc", NumeralSystem.HEX);
        Number secondNumber = new Number("a", NumeralSystem.HEX);

        Number result = HexBinOctCalculator.divide(firstNumber, secondNumber, NumeralSystem.HEX);

        assertEquals("12", result.getValue());
    }

    @Test
    public void canAddOneOctOneBinNumbersAndConvertResultToHex() {
        Number firstNumber = new Number("7", NumeralSystem.OCT);
        Number secondNumber = new Number("1010", NumeralSystem.BIN);

        Number result = HexBinOctCalculator.add(firstNumber, secondNumber, NumeralSystem.HEX);

        assertEquals("11", result.getValue());
    }

    @Test
    public void cabSubtractTwoOctNumbers() {
        Number firstNumber = new Number("35", NumeralSystem.OCT);
        Number secondNumber = new Number("17", NumeralSystem.OCT);

        Number result = HexBinOctCalculator.subtract(firstNumber, secondNumber, NumeralSystem.OCT);

        assertEquals("16", result.getValue());
    }

    @Test
    public void canMultiplyTwoOctNumbers() {
        Number firstNumber = new Number("14", NumeralSystem.OCT);
        Number secondNumber = new Number("5", NumeralSystem.OCT);

        Number result = HexBinOctCalculator.multiply(firstNumber, secondNumber, NumeralSystem.OCT);

        assertEquals("74", result.getValue());
    }

    @Test
    public void canDivideTwoOctNumbers() {
        Number firstNumber = new Number("71", NumeralSystem.OCT);
        Number secondNumber = new Number("6", NumeralSystem.OCT);

        Number result = HexBinOctCalculator.divide(firstNumber, secondNumber, NumeralSystem.OCT);

        assertEquals("11", result.getValue());
    }

    @Test
    public void canMultiplyTwoNegativeBinNumbers() {
        Number firstNumber = new Number("11111111111111111111111111101111", NumeralSystem.BIN);
        Number secondNumber = new Number("11111111111111111111111111111110", NumeralSystem.BIN);

        Number result = HexBinOctCalculator.multiply(firstNumber, secondNumber, NumeralSystem.BIN);

        assertEquals("100010", result.getValue());
    }

    @Test
    public void canDivideFirstNegativeSecondPositiveBinNumbers() {
        Number firstNumber = new Number("11111111111111111111111111100101", NumeralSystem.BIN);
        Number secondNumber = new Number("11", NumeralSystem.BIN);

        Number result = HexBinOctCalculator.divide(firstNumber, secondNumber, NumeralSystem.BIN);

        assertEquals("11111111111111111111111111110111", result.getValue());
    }

    @Test
    public void canDivideTwoNegativeHexNumbers() {
        Number firstNumber = new Number("ffffffc1", NumeralSystem.HEX);
        Number secondNumber = new Number("fffffffd", NumeralSystem.HEX);

        Number result = HexBinOctCalculator.divide(firstNumber, secondNumber, NumeralSystem.HEX);

        assertEquals("15", result.getValue());
    }

    @Test
    public void canMultiplyFirstNegativeSecondPositiveHexNumbers() {
        Number firstNumber = new Number("fffffffa", NumeralSystem.HEX);
        Number secondNumber = new Number("16", NumeralSystem.HEX);

        Number result = HexBinOctCalculator.multiply(firstNumber, secondNumber, NumeralSystem.HEX);

        assertEquals("ffffff7c", result.getValue());
    }

    @Test
    public void canMultiplyTwoNegativeOctNumbers() {
        Number firstNumber = new Number("37777777724", NumeralSystem.OCT);
        Number secondNumber = new Number("37777777707", NumeralSystem.OCT);

        Number result = HexBinOctCalculator.multiply(firstNumber, secondNumber, NumeralSystem.OCT);

        assertEquals("4714", result.getValue());
    }

    @Test
    public void canDivideFirstPositiveSecondNegativeOctNumbers() {
        Number firstNumber = new Number("55", NumeralSystem.OCT);
        Number secondNumber = new Number("37777777761", NumeralSystem.OCT);

        Number result = HexBinOctCalculator.divide(firstNumber, secondNumber, NumeralSystem.OCT);

        assertEquals("37777777775", result.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotDivideToZero() {
        Number firstNumber = new Number("23", NumeralSystem.HEX);
        Number secondNumber = new Number("0", NumeralSystem.HEX);

        Number result = HexBinOctCalculator.divide(firstNumber, secondNumber, NumeralSystem.HEX);
    }
}
