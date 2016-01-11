package ru.unn.agile.Metrics.model;

public enum Metric {
    RHO_INF(0.0),
    RHO_ONE(1.0),
    RHO_TWO(2.0),
    RHO_THREE(3.0),
    RHO_FOUR(4.0);

    private final double param;

    Metric(final double param) {
        this.param = param;
    }

    double getParam() {
        return param;
    }
}
