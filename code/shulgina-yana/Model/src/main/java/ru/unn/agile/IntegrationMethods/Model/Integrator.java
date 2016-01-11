package ru.unn.agile.IntegrationMethods.Model;

public class Integrator {
    static final double DEFAULT_STEP = 0.0001;
    private double lowerLimit;
    private double upperLimit;
    private double step;
    private IFunction function;
    private double integralValue;

    static final double ODD_TERMS_SIMPSON_COEF = 4.0;
    static final double EVEN_TERMS_SIMPSON_COEF = 2.0;
    static final double FINAL_SIMPSON_COEF = 3.0;

    public Integrator(final double lowerLim, final double upperLim, final IFunction function) {
        this.lowerLimit = lowerLim;
        this.upperLimit = upperLim;
        step = DEFAULT_STEP;
        this.function = function;
        integralValue = 0.0;
    }

    public double leftRectangles() {
        for (double x = lowerLimit; x < upperLimit; x += step) {
            integralValue += function.getValue(x);
        }
        integralValue *= step;

        return integralValue;
    }

    public double rightRectangles() {
        for (double x = lowerLimit + step; x <= upperLimit; x += step) {
            integralValue += function.getValue(x);
        }
        integralValue *= step;

        return integralValue;
    }

    public double midpointRectangles() {
        for (double x = lowerLimit + step / 2.0; x < upperLimit; x += step) {
            integralValue += function.getValue(x);
        }
        integralValue *= step;

        return integralValue;
    }

    public double trapezes() {
        for (double x = lowerLimit; x < upperLimit; x += step) {
            integralValue += (function.getValue(x) + function.getValue(x + step)) / 2.0;
        }
        integralValue *= step;

        return integralValue;
    }

    public double simpson() {
        double smallStep = step / 2.0;

        integralValue += function.getValue(lowerLimit) + function.getValue(upperLimit);
        for (double x = lowerLimit + smallStep; x < upperLimit; x += step) {
            integralValue += ODD_TERMS_SIMPSON_COEF * function.getValue(x);
        }
        for (double x = lowerLimit + step; x < upperLimit; x += step) {
            integralValue += EVEN_TERMS_SIMPSON_COEF * function.getValue(x);
        }
        integralValue *= smallStep / FINAL_SIMPSON_COEF;

        return integralValue;
    }

    public void setLowerLimit(final double lowerLim) {
        this.lowerLimit = lowerLim;
    }

    public double getLowerLimit() {
        return lowerLimit;
    }

    public void setUpperLimit(final double upperLim) {
        this.upperLimit = upperLim;
    }

    public double getUpperLimit() {
        return upperLimit;
    }

    public void setStep(final double step) {
        this.step = step;
    }

    public double getStep() {
        return step;
    }

    public double getIntegralValue() {
        return integralValue;
    }

    public void setFunction(final IFunction function) {
        this.function = function;
    }

    public IFunction getFunction() {
        return function;
    }
}
