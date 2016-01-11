package ru.unn.agile.IntegrationMethods.Model;

public class ExpFunction implements IFunction {
    public double getValue(final double x) {
        return Math.exp(x);
    }
}
