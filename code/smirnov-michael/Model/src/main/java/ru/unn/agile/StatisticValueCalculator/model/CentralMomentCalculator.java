package ru.unn.agile.StatisticValueCalculator.model;

import java.util.Collection;

public class CentralMomentCalculator extends MomentCalculator {
    private final MeanCalculator meanCalculator;

    public CentralMomentCalculator(final int order) {
        super(order, 0.0);
        meanCalculator = new MeanCalculator();
    }

    @Override
    public double calculate(final Collection<Double> dataForStatistics) {
        double mean = meanCalculator.calculate(dataForStatistics);
        super.setOffset(mean);

        return super.calculate(dataForStatistics);
    }
}
