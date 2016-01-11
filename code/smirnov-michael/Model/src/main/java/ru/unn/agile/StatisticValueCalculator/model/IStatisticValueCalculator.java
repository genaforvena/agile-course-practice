package ru.unn.agile.StatisticValueCalculator.model;

import java.util.Collection;

public interface IStatisticValueCalculator {
    double calculate(final Collection<Double> dataForStatistics);
}
