package ru.unn.agile.StatisticValueCalculator.model;

import java.security.InvalidParameterException;
import java.util.Collection;

public class MomentCalculator implements IStatisticValueCalculator {
    private static final double MOMENT_OF_EMPTY_DATA = 0.0;
    private final int order;
    private double offset;

    public MomentCalculator(final int order, final double offset) {
        if (order <= 0) {
            throw new InvalidParameterException("Order must be >= 0");
        }

        this.order = order;
        this.offset = offset;
    }

    public void setOffset(final double newOffset) {
        offset = newOffset;
    }

    @Override
    public double calculate(final Collection<Double> dataForStatistics) {
        if (dataForStatistics == null) {
            throw new NullPointerException("Parameter dataForStatistics must not be null");
        }

        if (dataForStatistics.isEmpty()) {
            return MOMENT_OF_EMPTY_DATA;
        }

        int sizeOfData = dataForStatistics.size();

        double moment = 0.0;
        for (Double instanceOfData : dataForStatistics) {
            moment += Math.pow(instanceOfData - offset, order);
        }
        moment /= sizeOfData;

        return moment;
    }
}
