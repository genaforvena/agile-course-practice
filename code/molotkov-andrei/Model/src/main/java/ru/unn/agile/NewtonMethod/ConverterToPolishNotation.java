package ru.unn.agile.NewtonMethod;

import java.util.Stack;

enum Priority { low, equally, bracket, addDiff, multDiv }

public class ConverterToPolishNotation {
    private final Stack<String> reversPolishStack;
    private final Stack<String> operationStack;
    private final Stack<Character> leftBracketsStack;
    private int indexChar;

    public ConverterToPolishNotation() {
        reversPolishStack = new Stack<>();
        operationStack     = new Stack<>();
        leftBracketsStack = new Stack<>();
        indexChar = 0;
    }

    public String convert(final String infixExpression) {
        char character = infixExpression.charAt(0);
        if (!isCorrectFirstCharacter(character)) {
            throw  new ArithmeticException("Incorrect first character");
        }
        char tempCharacter;
        indexChar = 0;
        do {
            character = infixExpression.charAt(indexChar++);
            if (Character.isAlphabetic(character)) {
                reversPolishStack.push(Character.toString(character));
                tempCharacter = infixExpression.charAt(indexChar);
                if (!isAllowableCaseForVariable(tempCharacter)) {
                    throw new ArithmeticException("Incorrect character after variable");
                }
            } else if (Character.isDigit(character)) {
                tempCharacter = infixExpression.charAt(indexChar);
                if (!isAllowableCaseForDigit(tempCharacter)) {
                    throw new ArithmeticException("Incorrect character after digit");
                }
                indexChar--;
                collectingNumber(infixExpression);
            } else if (character == '(') {
                operationStack.push(Character.toString(character));
                leftBracketsStack.push(character);
                tempCharacter = infixExpression.charAt(indexChar);
                if (!isAllowableCaseForLeftBracket(tempCharacter)) {
                    throw new ArithmeticException("Incorrect character after left bracket");
                }
            } else if (character == ')') {
                if (leftBracketsStack.isEmpty()) {
                    throw new ArithmeticException("Missing left bracket");
                }
                leftBracketsStack.pop();
                tempCharacter = infixExpression.charAt(indexChar);
                if (!isAllowableCaseForRightBracket(tempCharacter)) {
                    throw new ArithmeticException("Incorrect character after right bracket");
                }
                calculateInBrackets();
            } else if (isOperator(character)) {
                setOperatorToStack(character);
                if (character == '=') {
                    break;
                }
                tempCharacter = infixExpression.charAt(indexChar);
                if (!isAllowableCaseForOperator(tempCharacter)) {
                    throw new ArithmeticException("Incorrect character after operator");
                }
            }
        } while (character != '=' && indexChar < infixExpression.length());
        return createPolishExpression();
    }

    private Priority getOperationPriority(final char operator) {
        Priority priority = Priority.low;
        switch (operator) {
            case '*':
            case '/':
                priority = Priority.multDiv;
                break;
            case '+':
            case '-':
                priority = Priority.addDiff;
                break;
            case '(':
                priority = Priority.bracket;
                break;
            case '=':
                priority = Priority.equally;
                break;
            default:
        }
        return priority;
    }

    private boolean isOperator(final char character) {
        switch (character) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '=':
                return true;
            default:
                return false;
        }
    }

    private void collectingNumber(final String infixExpression) {
        char ch = infixExpression.charAt(indexChar++);
        String digit = new String();
        digit = Character.toString(ch);
        ch = infixExpression.charAt(indexChar++);
        while (Character.isDigit(ch)) {
            digit += ch;
            ch = infixExpression.charAt(indexChar++);
        }
        reversPolishStack.push(digit);
        indexChar--;
    }

    private String createPolishExpression() {
        String polishExpression = new String();
        Stack<String> polishStack = new Stack<String>();

        while (!reversPolishStack.isEmpty()) {
            polishStack.push(reversPolishStack.pop());
        }

        while (!polishStack.isEmpty()) {
            polishExpression += polishStack.pop();
        }

        polishExpression += "=";

        return polishExpression;
    }

    private void setOperatorToStack(final char ch) {
        while (!operationStack.isEmpty()) {
            char t = operationStack.pop().charAt(0);
            if (getOperationPriority(ch).ordinal() <= getOperationPriority(t).ordinal()) {
                reversPolishStack.push(Character.toString(t));
            } else {
                operationStack.push(Character.toString(t));
                break;
            }
        }
        operationStack.push(Character.toString(ch));
    }

    private void calculateInBrackets() {
        while (true) {
            char tempChar = operationStack.pop().charAt(0);
            if (tempChar == '(') {
                break;
            }
            reversPolishStack.push(Character.toString(tempChar));
        }
    }

    private boolean isAllowableCaseForVariable(final char character) {
        return isOperator(character) || isRightBracket(character);
    }

    private boolean isAllowableCaseForDigit(final char character) {
        return isAllowableCaseForVariable(character) || Character.isDigit(character);
    }

    private boolean isAllowableCaseForOperator(final char character) {
        return Character.isAlphabetic(character) || Character.isDigit(character)
                || isLeftBracket(character);
    }

    private boolean isCorrectFirstCharacter(final char character) {
        return isAllowableCaseForOperator(character);
    }

    private boolean isAllowableCaseForLeftBracket(final char character) {
        return Character.isAlphabetic(character) || Character.isDigit(character)
                || isRightBracket(character);
    }

    private boolean isAllowableCaseForRightBracket(final char character) {
        return isOperator(character) || isRightBracket(character);
    }

    private boolean isRightBracket(final char character) {
        return character == ')';
    }

    private boolean isLeftBracket(final char character) {
        return character == '(';
    }
}
