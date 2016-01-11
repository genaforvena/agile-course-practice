package ru.unn.agile.HexBinOctCalculator.Model;

public final class HexBinOctCalculator {
    private HexBinOctCalculator() {

    }

    private static String convertResult(final Integer res, final NumeralSystem system) {
        switch (system) {
            case HEX:
                return Integer.toHexString(res);
            case BIN:
                return Integer.toBinaryString(res);
            case OCT:
                return Integer.toOctalString(res);
            default:
                return Integer.toString(res);
        }
    }

    private static int parseString(final String number, final NumeralSystem system) {
        return (int) Long.parseLong(number, system.getValue());
    }

    public static Number add(final Number firstNumber, final Number secondNumber,
                             final NumeralSystem resultSystem) {
        int left = parseString(firstNumber.getValue(), firstNumber.getSystem());
        int right = parseString(secondNumber.getValue(), secondNumber.getSystem());
        String sum = convertResult(left + right, resultSystem);
        return new Number(sum, resultSystem);
    }

    public static Number subtract(final Number firstNumber, final Number secondNumber,
                                  final NumeralSystem resultSystem) {
        int left = parseString(firstNumber.getValue(), firstNumber.getSystem());
        int right = parseString(secondNumber.getValue(), secondNumber.getSystem());
        String diff = convertResult(left - right, resultSystem);
        return new Number(diff, resultSystem);
    }

    public static Number multiply(final Number firstNumber, final Number secondNumber,
                                  final NumeralSystem resultSystem) {
        int left = parseString(firstNumber.getValue(), firstNumber.getSystem());
        int right = parseString(secondNumber.getValue(), secondNumber.getSystem());
        String prod = convertResult(left * right, resultSystem);
        return new Number(prod, resultSystem);
    }

    public static Number divide(final Number firstNumber, final Number secondNumber,
                                final NumeralSystem resultSystem) {
        int left = parseString(firstNumber.getValue(), firstNumber.getSystem());
        int right = parseString(secondNumber.getValue(), secondNumber.getSystem());
        if (right == 0) {
            throw new IllegalArgumentException("Division by zero!");
        } else {
            String quotient = convertResult(left / right, resultSystem);
            return new Number(quotient, resultSystem);
        }
    }

    public enum Operation {
        ADD("+") {
            public Number apply(final Number left, final Number right, final NumeralSystem system) {
                return add(left, right, system);
            }
        },
        SUBTRACT("-") {
            public Number apply(final Number left, final Number right, final NumeralSystem system) {
                return subtract(left, right, system);
            }
        },
        MULTIPLY("*") {
            public Number apply(final Number left, final Number right, final NumeralSystem system) {
                return multiply(left, right, system);
            }
        },
        DIVIDE("/") {
            public Number apply(final Number left, final Number right, final NumeralSystem system) {
                return divide(left, right, system);
            }
        };

        private final String name;
        Operation(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        public abstract Number apply(final Number l, final Number r, final NumeralSystem system);
    }
}
