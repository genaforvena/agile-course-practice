package ru.unn.agile.LongArithmetic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WhenWorkWithLongNumber {

    private LongNumber longNumber1;
    private LongNumber longNumber2;
    private LongNumber result;
    private String stringNumber;

    @Test
    public void add1To0() {
        initializePairLongNumber(1, 0);
        result = longNumber1.add(longNumber2);

        stringNumber = result.convertToString();
        assertEquals("1", stringNumber);
    }

    private void initializePairLongNumber(final int number1, final int number2) {
        longNumber1 = new LongNumber(number1);
        longNumber2 = new LongNumber(number2);
    }

    @Test
    public void add122To78() {
        initializePairLongNumber(122, 78);
        result = longNumber1.add(longNumber2);

        stringNumber = result.convertToString();
        assertEquals("200", stringNumber);
    }

    @Test
    public void addMinus1To2() {
        initializePairLongNumber(-1, 2);
        result = longNumber1.add(longNumber2);

        stringNumber = result.convertToString();
        assertEquals("1", stringNumber);
    }

    @Test
    public void addMinus199To199() {
        initializePairLongNumber(-199, 199);
        result = longNumber1.add(longNumber2);

        stringNumber = result.convertToString();
        assertEquals("0", stringNumber);
    }

    @Test
    public void addMinusLongToLong() {
        longNumber1 = new LongNumber("-10000000000011111111111");
        longNumber2 = new LongNumber("12345678998799999999999");
        result = longNumber1.add(longNumber2);

        stringNumber = result.convertToString();
        assertEquals("2345678998788888888888", stringNumber);
    }

    @Test
    public void addMinusLongToMinusLong() {
        longNumber1 = new LongNumber("-10000000000011111111111");
        longNumber2 = new LongNumber("-10000000000011111111111");
        result = longNumber1.add(longNumber2);

        stringNumber = result.convertToString();
        assertEquals("-20000000000022222222222", stringNumber);
    }

    @Test
    public void longAdd() {
        longNumber1 = new LongNumber("10000000000011111111111");
        longNumber2 = new LongNumber("12345678998799999999999");
        result = longNumber1.add(longNumber2);

        stringNumber = result.convertToString();
        assertEquals("22345678998811111111110", stringNumber);
    }

    @Test
    public void multiply0And5() {
        initializePairLongNumber(0, 5);
        result = longNumber1.multiply(longNumber2);

        stringNumber = result.convertToString();
        assertEquals("0", stringNumber);
    }

    @Test
    public void multiply23000And43() {
        initializePairLongNumber(23099, 43);
        result = longNumber1.multiply(longNumber2);

        stringNumber = result.convertToString();
        assertEquals("993257", stringNumber);
    }

    @Test
    public void multiply23And135() {
        initializePairLongNumber(23, 135);
        result = longNumber1.multiply(longNumber2);

        stringNumber = result.convertToString();
        assertEquals("3105", stringNumber);
    }

    @Test
    public void longMultiply() {
        longNumber1 = new LongNumber("1000000000001");
        longNumber2 = new LongNumber("123456789987");
        result = longNumber1.multiply(longNumber2);

        stringNumber = result.convertToString();
        assertEquals("123456789987123456789987", stringNumber);
    }

    @Test
    public void multiply23AndMinus135() {
        initializePairLongNumber(23, -135);
        result = longNumber1.multiply(longNumber2);

        stringNumber = result.convertToString();
        assertEquals("-3105", stringNumber);
    }

    @Test
    public void minusLongMultiply() {
        longNumber1 = new LongNumber("-1000000000001");
        longNumber2 = new LongNumber("-123456789987");
        result = longNumber1.multiply(longNumber2);

        stringNumber = result.convertToString();
        assertEquals("123456789987123456789987", stringNumber);
    }

    @Test
    public void multiply0AndMinus135() {
        initializePairLongNumber(0, -135);
        result = longNumber1.multiply(longNumber2);

        stringNumber = result.convertToString();
        assertEquals("0", stringNumber);
    }

    @Test
    public void compare0And1() {
        initializePairLongNumber(0, 1);
        boolean isEqual = longNumber1.equals(longNumber2);

        assertFalse(isEqual);
    }

    @Test
    public void compare11And111() {
        initializePairLongNumber(11, 111);
        boolean isEqual = longNumber1.equals(longNumber2);

        assertFalse(isEqual);
    }

    @Test
    public void compare2222222And2222222() {
        initializePairLongNumber(2222222, 2222222);
        boolean isEqual = longNumber1.equals(longNumber2);

        assertTrue(isEqual);
    }

    @Test
    public void convert0ToString() {
        longNumber1 = new LongNumber();
        stringNumber = longNumber1.convertToString();

        assertEquals("0", stringNumber);
    }

    @Test
    public void convert98123476ToString() {
        longNumber1 = new LongNumber(98123476);
        stringNumber = longNumber1.convertToString();

        assertEquals("98123476", stringNumber);
    }

    @Test
    public void convertLongToString() {
        longNumber1 = new LongNumber("19283746519283746519283746519283745619");
        stringNumber = longNumber1.convertToString();

        assertEquals("19283746519283746519283746519283745619", stringNumber);
    }

    @Test
    public void convertLongNumber1Toint() {
        longNumber1 = new LongNumber(1);
        int number = longNumber1.convertToInt();

        assertEquals(1, number);
    }

    @Test
    public void convertMinusLongNumberToint() {
        longNumber1 = new LongNumber(-234123498);
        int number = longNumber1.convertToInt();

        assertEquals(-234123498, number);
    }

    @Test
    public void convertLongNumberMinus0Toint() {
        longNumber1 = new LongNumber(0);
        int number = longNumber1.convertToInt();

        assertEquals(0, number);
    }

    @Test(expected = LongNumberCanNotConvertExeption.class)
    public void convertVeryLongNumberToint() {
        longNumber1 = new LongNumber("19283746519283746519283746519283745619");
        int number = longNumber1.convertToInt();
    }

    @Test
    public void defaultNumberIsNotUndefined() {
        longNumber1 = new LongNumber();

        boolean isUndefined = longNumber1.isUndefined();
        assertFalse(isUndefined);
    }

    @Test
    public void longNumberIsNotUndefined() {
        longNumber1 = new LongNumber("123456789987654321");

        boolean isUndefined = longNumber1.isUndefined();
        assertFalse(isUndefined);
    }
}
