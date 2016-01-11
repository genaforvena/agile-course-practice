package ru.unn.agile.LongArithmetic;

import java.math.BigInteger;
import java.util.Arrays;

public class LongNumber {

    public static final int[] UNDEFINED_VALUE = null;
    public static final LongNumber UNDEFINED = null;

    private static final int SCALE = 10;
    private int rank;
    private int sign;
    private int[] value;

    public LongNumber() {
        rank = 1;
        value = new int[this.rank];
        sign = 1;
        value[0] = 0;
    }

    public LongNumber(final int number) {
        rank = getIntRank(number);
        value = new int[rank];
        sign = 1;
        if (number < 0) {
            sign = -1;
        }
        initialize(Math.abs(number));
    }

    private int getIntRank(final int number) {
        int rank = 1;
        if (number != 0) {
            rank = (int) (Math.log(Math.abs(number)) / Math.log(SCALE) + 1);
        }

        return rank;
    }

    private void initialize(final int number) {
        int intermediateNum = number;
        int newElement;
        int divider;
        int remainer;

        for (int j = this.rank - 1; j > -1; j--) {
            divider = (int) (Math.pow(SCALE, j));
            newElement = intermediateNum / divider;
            this.value[j] = newElement;
            remainer = intermediateNum % divider;
            intermediateNum = remainer;
        }
    }

    public LongNumber(final char[] chars) {
        String string = new String(chars);
        initialize(string);
    }

    public LongNumber(final String string) {
        initialize(string);
    }

    private void initialize(final String string) {
        this.rank = string.length();
        this.sign = 1;
        if (string.charAt(0) == '-') {
            this.sign = -1;
            this.rank--;
        }
        this.value = new int[rank];
        this.fillValue(string);
    }

    private void fillValue(final String string) {
        char charElement;
        int newElement;
        int lenght = string.length();

        int index = this.rank - 1;
        for (int i = 0; i < lenght; i++, index--) {
            charElement = string.charAt(i);
            if (i == 0 && charElement == '-') {
                index++;
            } else {
                newElement = Character.getNumericValue(charElement);
                final int maxCharNumberIndex = 9;
                if (newElement <= maxCharNumberIndex) {
                    this.value[index] = newElement;
                } else {
                    this.value = null;
                    this.rank = 0;
                    break;
                }
            }
        }
    }

    public LongNumber(final LongNumber copiedNum) {
        this.rank = copiedNum.rank;
        this.value = new int[this.rank];
        this.sign = copiedNum.sign;
        if (copiedNum.value == LongNumber.UNDEFINED_VALUE) {
            this.value = LongNumber.UNDEFINED_VALUE;
        } else {
            System.arraycopy(copiedNum.value, 0, this.value, 0, this.rank);
        }
    }

    public LongNumber add(final LongNumber longNumber) {
        LongNumber result = new LongNumber();
        LongNumber addendum1 = new LongNumber(this);
        LongNumber addendum2 = new LongNumber(longNumber);

        int maxRank = Math.max(addendum1.rank, addendum2.rank);
        result.rank = maxRank + 1;
        result.value = new int[result.rank];

        Arrays.fill(result.value, 0);
        result.summarize(addendum1, addendum2);

        return result;
    }

    private void summarize(final LongNumber addendum1, final LongNumber addendum2) {
        int maxRank = Math.max(addendum1.rank, addendum2.rank);
        addendum1.beforePreparation();
        addendum2.beforePreparation();

        int smallSum = 0;
        for (int i = 0; i < maxRank; i++) {
            if (addendum1.rank > i && addendum2.rank > i) {
                smallSum = addendum1.value[i] + addendum2.value[i] + this.value[i];
            } else if (addendum1.rank > i) {
                smallSum = addendum1.value[i] + this.value[i];
            } else if (addendum2.rank > i) {
                smallSum = addendum2.value[i] + this.value[i];
            } else {
                smallSum = this.value[i];
            }

            if (smallSum >= SCALE) {
                this.value[i + 1] = 1;
                this.value[i] = smallSum % SCALE;
            } else {
                this.value[i] = smallSum;
            }
        }

        this.deleteZeroes();
        this.definitionOfSign();
    }

    private void beforePreparation() {
        if (this.sign == -1) {
            for (int i = 0; i < this.rank; i++) {
                this.value[i] *= this.sign;
            }
        }
    }

    private void deleteZeroes() {
        int lastElement = this.rank - 1;
        while (this.value[lastElement] == 0 && lastElement != 0) {
            int[] newValue = new int[lastElement];
            System.arraycopy(this.value, 0, newValue, 0, lastElement);
            this.value = newValue;
            this.rank--;
            lastElement = this.rank - 1;
        }
    }

    private void definitionOfSign() {
        boolean isNegative = true;
        this.sign = 1;

        for (int i = 0; i < this.rank; i++) {
            if (this.value[i] > 0) {
                isNegative = false;
                break;
            }
        }
        if (isNegative && !this.equals(0)) {
            this.sign = -1;
            for (int i = 0; i < this.rank; i++) {
                this.value[i] *= this.sign;
            }
        } else {
            this.sign = 1;
            for (int i = 0; i < this.rank; i++) {
                if (this.value[i] < 0) {
                    this.value[i + 1]--;
                    this.value[i] = SCALE + this.value[i];
                }
            }
        }
        this.deleteZeroes();
    }

    public LongNumber multiply(final LongNumber longNumber) {
        LongNumber result;

        String stringNumber = this.convertToString();
        BigInteger firstMultiplier = new BigInteger(stringNumber);
        stringNumber = longNumber.convertToString();
        BigInteger secondMultiplier = new BigInteger(stringNumber);

        BigInteger bigResult = firstMultiplier.multiply(secondMultiplier);
        stringNumber = bigResult.toString();
        result = new LongNumber(stringNumber);

        return result;
    }

    public String convertToString() {
        String stringNumber = "";
        if (this.sign == -1) {
            stringNumber += "-";
        }
        for (int i = 0, j = this.rank - 1; i < this.rank; i++, j--) {
            int element = this.value[j];
            stringNumber += Integer.toString(element);
        }

        return stringNumber;
    }

    public int convertToInt() {
        int intNum = 0;

        int maxRank = this.getIntRank(Integer.MAX_VALUE);
        if (this.rank <= maxRank) {
            for (int i = 0; i < this.rank; i++) {
                intNum += this.value[i] * Math.pow(SCALE, i);
            }
            intNum *= this.sign;
        } else {
            throw new LongNumberCanNotConvertExeption("This long number can't convert to int");
        }

        return intNum;
    }

    @Override
    public int hashCode() {
        return this.value.hashCode() * this.sign;
    }

    @Override
    public boolean equals(final Object object) {
        LongNumber longNumber = (LongNumber) object;
        boolean isEqual = true;

        if (this.rank == longNumber.rank && this.sign == longNumber.sign) {
            isEqual = Arrays.equals(this.value, longNumber.value);
        } else {
            isEqual = false;
        }

        return isEqual;
    }

    public boolean equals(final int number) {
        boolean result = true;
        LongNumber newNum = new LongNumber(number);

        result = this.equals(newNum);

        return result;
    }

    public boolean isUndefined() {
        boolean result = false;
        if (this.value == LongNumber.UNDEFINED_VALUE) {
            result = true;
        }
        return result;
    }

    public void setValue(final int newValue) {
        LongNumber newNum = new LongNumber(newValue);
        this.rank = newNum.rank;
        this.value = newNum.value;
    }
}
