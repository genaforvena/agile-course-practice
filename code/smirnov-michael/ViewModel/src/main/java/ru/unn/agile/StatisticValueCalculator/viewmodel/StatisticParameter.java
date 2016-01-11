package ru.unn.agile.StatisticValueCalculator.viewmodel;

public enum StatisticParameter {
    ORDER("Order"),
    EVENT("Event");

    private final String name;

    StatisticParameter(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
