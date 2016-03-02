package ru.unn.agile.ElasticityOfDemand;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ElasticityOfDemandCalculator {
    private static final int CHANGE_IN_PRICE_SCALE = 4;

    public double calculate(final BigDecimal priceNew, final BigDecimal priceOld,
                            final long demandQuantityNew, final long demandQuantityOld) {
        if (!isBigDecimalValid(priceNew) || !isBigDecimalValid(priceOld)
                || demandQuantityNew <= 0 || demandQuantityOld <= 0) {
            throw new IllegalArgumentException(
                    String.format("Invalid input! priceNew: %s, priceOld: %s, "
                            + "demandQuantityNew: %d, demandQuantityOld: %d",
                    priceNew, priceOld, demandQuantityNew, demandQuantityOld));
        }
        return calculateChangeInDemand(demandQuantityNew, demandQuantityOld)
                / calculateChangeInPrice(priceNew, priceOld);
    }

    double calculateChangeInDemand(final long demandQuantityNew, final long demandQuantityOld) {
        return (double) (demandQuantityNew - demandQuantityOld) / demandQuantityOld;
    }

    double calculateChangeInPrice(final BigDecimal priceNew, final BigDecimal priceOld) {
        return priceNew.subtract(priceOld)
                .divide(priceOld, CHANGE_IN_PRICE_SCALE, RoundingMode.CEILING).doubleValue();
    }

    private boolean isBigDecimalValid(final BigDecimal bigDecimal) {
        return bigDecimal != null && bigDecimal.compareTo(BigDecimal.ZERO) > 0;
    }
}
