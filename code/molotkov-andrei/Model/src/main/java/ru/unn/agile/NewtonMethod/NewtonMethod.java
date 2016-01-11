package ru.unn.agile.NewtonMethod;

public class NewtonMethod {
    private final Function basicFunc;
    private final Function derivativeFunc;
    private double previousPoint;
    private double nextPoint;

    public NewtonMethod(final String basicFunc, final String derivativeFunc) {
        this.basicFunc = new Function(basicFunc);
        this.derivativeFunc = new Function(derivativeFunc);
    }

    public Double searchRoot(final double leftPointRange, final double rightPointRange) {
        if (!isMonotonicFunction(leftPointRange, rightPointRange)) {
            throw new IllegalArgumentException("Function is not monotonic");
        }
        if (!rootIsInRange(leftPointRange, rightPointRange)) {
            throw new IllegalArgumentException("Root is not in range");
        }
        previousPoint = rightPointRange;
        nextPoint = calculateNextPoint(previousPoint);
        while (!isConverged()) {
            previousPoint = nextPoint;
            nextPoint = calculateNextPoint(previousPoint);
        }
        return nextPoint;
    }

    private double calculateNextPoint(final double point) {
        return point - basicFunc.calculate(point) / derivativeFunc.calculate(point);
    }

    private boolean isConverged() {
        final double eps = 0.000001;
        return Math.abs(nextPoint - previousPoint) < eps;
    }

    private boolean rootIsInRange(final double leftPointRange, final double rightPointRange) {
        return basicFunc.calculate(leftPointRange) * basicFunc.calculate(rightPointRange) < 0;
    }

    private int getSignDerivative(final double point) {
        double signDerivative = derivativeFunc.calculate(point);
        if (signDerivative > 0) {
            return 1;
        } else if (signDerivative < 0) {
            return -1;
        }
        return 0;
    }

    private boolean isMonotonicFunction(final double leftPointRange,
                                        final double rightPointRange) {
        final int countRangesForMonotonic = 10;
        final int countPointsForMonotonic = countRangesForMonotonic + 1;
        double stepLength = (rightPointRange - leftPointRange) / countRangesForMonotonic;
        boolean result = true;

        int signDerivativeFirstPoint = getSignDerivative(leftPointRange);
        int signDerivativeSecondPoint = getSignDerivative(leftPointRange + stepLength);

        if (signDerivativeFirstPoint != signDerivativeSecondPoint) {
            return false;
        }
        int signDerivative;
        double nextPoint;
        for (int numberPoint = 2; numberPoint < countPointsForMonotonic; numberPoint++) {
            nextPoint = leftPointRange + stepLength * numberPoint;
            signDerivative = getSignDerivative(nextPoint);
            if (signDerivative != signDerivativeFirstPoint) {
                result = false;
                break;
            }
        }
        return result;
    }
}
