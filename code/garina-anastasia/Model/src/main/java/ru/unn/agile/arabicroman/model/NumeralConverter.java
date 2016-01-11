package ru.unn.agile.arabicroman.model;

public final class NumeralConverter {
    private static final String [] PRIMARY_ROMAN_SYMBOLS = {"I", "V", "X", "L", "C", "D", "M"};
    private static final int [] NUMBERS_RELATED_TO_ROMAN_SYMBOLS = {1, 5, 10, 50, 100, 500, 1000};

    private NumeralConverter() {
    }

    public static String convert(final int arabicNumber) {
        final int maxValidNumber = 3999;
        if (arabicNumber < 0 || arabicNumber > maxValidNumber) {
            throw new IllegalArgumentException();
        } else {
            String romanNumber = "";
            if (arabicNumber != 0) {
                int divisionResult;
                int tempNumber = arabicNumber;
                int positionInNumbersArrays = PRIMARY_ROMAN_SYMBOLS.length - 1;
                while (positionInNumbersArrays >= 0) {
                    divisionResult = tempNumber
                            / NUMBERS_RELATED_TO_ROMAN_SYMBOLS[positionInNumbersArrays];
                    romanNumber += simpleRomanNumber(divisionResult, positionInNumbersArrays);
                    tempNumber -= NUMBERS_RELATED_TO_ROMAN_SYMBOLS[positionInNumbersArrays]
                            * divisionResult;
                    positionInNumbersArrays -= 2;
                }
            }
            return romanNumber;
        }
    }

    private static String simpleRomanNumber(final int divisionResult, final int pos) {
        final int four = 4;
        final int five = 5;
        final int nine = 9;
        switch (divisionResult) {
            case 0:
                return "";
            case four:
                return PRIMARY_ROMAN_SYMBOLS[pos] + PRIMARY_ROMAN_SYMBOLS[pos + 1];
            case nine:
                return PRIMARY_ROMAN_SYMBOLS[pos] + PRIMARY_ROMAN_SYMBOLS[pos + 2];
            default:
                String simpleRoman = "";
                int tempNumber = divisionResult;
                if (tempNumber >= five) {
                    simpleRoman = PRIMARY_ROMAN_SYMBOLS[pos + 1];
                    tempNumber -= five;
                }
                while (tempNumber > 0) {
                    simpleRoman += PRIMARY_ROMAN_SYMBOLS[pos];
                    tempNumber--;
                }
                return simpleRoman;
        }
    }

    public static int convert(final String romanNumber) {
        if (romanNumber == null || romanNumber.isEmpty()) {
            return 0;
        }
        int arabicNumber = 0;
        int currentPositionOfSymbolInArray =
                getPosition(romanNumber.charAt(0));
        arabicNumber += NUMBERS_RELATED_TO_ROMAN_SYMBOLS[currentPositionOfSymbolInArray];
        for (int stringIndex = 1; stringIndex < romanNumber.length(); stringIndex++) {
            int positionOfNewSymbolInArray =
                    getPosition(romanNumber.charAt(stringIndex));
            if (newPositionSurpassOld(currentPositionOfSymbolInArray,
                    positionOfNewSymbolInArray)) {
                arabicNumber +=
                        NUMBERS_RELATED_TO_ROMAN_SYMBOLS[positionOfNewSymbolInArray]
                        - 2 * NUMBERS_RELATED_TO_ROMAN_SYMBOLS[currentPositionOfSymbolInArray];
            } else {
                if (positionOfNewSymbolInArray <= currentPositionOfSymbolInArray) {
                    arabicNumber += NUMBERS_RELATED_TO_ROMAN_SYMBOLS[positionOfNewSymbolInArray];
                } else {
                    throw new IllegalArgumentException();
                }
            }
            currentPositionOfSymbolInArray = positionOfNewSymbolInArray;
        }
        return arabicNumber;
    }

    private static int getPosition(final char romanSymbol) {
        for (int pos = 0; pos < PRIMARY_ROMAN_SYMBOLS.length; pos++) {
            if (PRIMARY_ROMAN_SYMBOLS[pos].equals(Character.toString(romanSymbol))) {
                return pos;
            }
        }
        throw new IllegalArgumentException();

    }

    private static boolean newPositionSurpassOld(final int oldPos, final int newPos) {
        if (newPos == (oldPos + 1) || newPos == (oldPos + 2)) {
            if (oldPos % 2 == 1) {
                throw new IllegalArgumentException();
            }
            return true;
        }
        return false;
    }
}
