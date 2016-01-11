package ru.unn.agile.StatisticValueCalculator.model;

import java.util.Collection;

public class ProbabilityOfEventCalculator implements IStatisticValueCalculator {
    private Double event;

    private static final double EPS_FOR_DOUBLE_COMPARISON = 1e-3;
    private static final double PROBABILITY_OF_EVENT_WITH_EMPTY_DATA = 0.0;

    public ProbabilityOfEventCalculator(final double event) {
        setEvent(event);
    }

    public void setEvent(final double event) {
        this.event = event;
    }

    @Override
    public double calculate(final Collection<Double> dataForStatistics) {
        if (dataForStatistics == null) {
            throw new NullPointerException("Parameter dataForStatistics must not be null");
        }

        if (event == null) {
            throw new NullPointerException("Event must not be null");
        }

        if (dataForStatistics.isEmpty()) {
            return PROBABILITY_OF_EVENT_WITH_EMPTY_DATA;
        }

        int sizeOfData = dataForStatistics.size();

        double probability = 0.0;

        for (Double instanceOfData : dataForStatistics) {
            if (areTwoDoublesEqual(instanceOfData, event)) {
                probability++;
            }
        }
        probability /= sizeOfData;
        return probability;
    }

    private boolean areTwoDoublesEqual(final double firstNumber, final double secondNumber) {
        return Math.abs(firstNumber - secondNumber) <= EPS_FOR_DOUBLE_COMPARISON;
    }
}
