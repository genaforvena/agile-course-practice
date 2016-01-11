package ru.unn.agile.NewtonMethod;

import java.util.Stack;

enum TypeComponent { add, diff, mult, div, digit, alpha, equally }

class FunctionComponent {
    private TypeComponent type;
    private int value;

    public void setType(final TypeComponent type) {
        this.type = type;
    }

    public TypeComponent getType() {
        return type;
    }

    public  void setValue(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

public class Function {
    private final Stack<Double> calculateStack;
    private final FunctionComponent []component;

    public Function(final String formula) {
        ConverterToPolishNotation converter = new ConverterToPolishNotation();
        String polishNotation = converter.convert(formula);
        component = new FunctionComponent[polishNotation.length()];
        for (int i = 0; i < polishNotation.length(); i++) {
            component[i] = new FunctionComponent();
        }
        calculateStack = new Stack<>();
        setComponents(polishNotation);
    }

    public double calculate(final double x) {
        int currentComponent = 0;
        double result = 0;
        while (component[currentComponent].getType() != TypeComponent.equally) {
            switch (component[currentComponent].getType()) {
                case digit:
                    calculateStack.push(Double.valueOf(component[currentComponent].getValue()));
                    break;
                case alpha:
                    calculateStack.push(Double.valueOf(x));
                    break;
                case add:
                    runOperation('+');
                    break;
                case diff:
                    runOperation('-');
                    break;
                case mult:
                    runOperation('*');
                    break;
                case div:
                    runOperation('/');
                    break;
                default:
            }
            currentComponent++;
        }
        if (!calculateStack.isEmpty()) {
            return calculateStack.pop();
        }
        return result;
    }

    private void setComponents(final String polishNotation) {
        char ch;
        int indexChar = 0;
        int indexComponent = 0;
        do {
            ch = polishNotation.charAt(indexChar++);
            switch (ch) {
                case '+':
                    component[indexComponent++].setType(TypeComponent.add);
                    break;
                case '-':
                    component[indexComponent++].setType(TypeComponent.diff);
                    break;
                case '*':
                    component[indexComponent++].setType(TypeComponent.mult);
                    break;
                case '/':
                    component[indexComponent++].setType(TypeComponent.div);
                    break;
                case '=':
                    component[indexComponent++].setType(TypeComponent.equally);
                    break;
                default:
                    if (Character.isAlphabetic(ch)) {
                        component[indexComponent++].setType(TypeComponent.alpha);
                    } else if (Character.isDigit(ch)) {
                        String digit = new String();
                        digit += ch;
                        ch = polishNotation.charAt(indexChar);
                        while (Character.isDigit(ch)) {
                            digit += ch;
                            ch = polishNotation.charAt(++indexChar);
                        }
                        ch = '?';
                        component[indexComponent].setType(TypeComponent.digit);
                        component[indexComponent++].setValue(Integer.valueOf(digit));
                    }
                    break;
            }
        } while (ch != '=' && indexChar < polishNotation.length());
    }

    private void runOperation(final char ch) {
        final double delta = 0.000000000001;
        boolean resultGetOperands = false;
        Double operand1 = 0.0;
        Double operand2 = 0.0;
        if (calculateStack.size() > 1) {
            operand1 = calculateStack.pop();
            operand2 = calculateStack.pop();
            resultGetOperands = true;
        } else {
            resultGetOperands = false;
        }
        if (resultGetOperands) {
            switch (ch) {
                case '+':
                    calculateStack.push(operand2 + operand1);
                    break;
                case '-':
                    calculateStack.push(operand2 - operand1);
                    break;
                case '*':
                    calculateStack.push(operand2 * operand1);
                    break;
                case '/':
                    if (operand1 < delta) {
                        calculateStack.clear();
                    } else {
                        calculateStack.push(operand2 / operand1);
                    }
                    break;
                default:
            }
        } else {
            calculateStack.clear();
        }
    }
}
