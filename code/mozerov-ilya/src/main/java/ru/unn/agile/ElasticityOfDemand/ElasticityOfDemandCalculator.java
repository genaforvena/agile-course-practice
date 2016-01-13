package ru.unn.agile.ElasticityOfDemand;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by imozerov on 12.01.16.
 */
public class ElasticityOfDemandCalculator {
    private static final int CHANGE_IN_PRICE_SCALE = 4;

    public double calculate(final BigDecimal aPriceNew, final BigDecimal aPriceOld,
                            final long aDemandQuantityNew, final long aDemandQuantityOld) {
        if (!isBigDecimalValid(aPriceNew) || !isBigDecimalValid(aPriceOld)
                || aDemandQuantityNew <= 0 || aDemandQuantityOld <= 0) {
            throw new IllegalArgumentException(
                    String.format("Invalid input! aPriceNew: %s, aPriceOld: %s, "
                            + "aDemandQuantityNew: %d, aDemandQuantityOld: %d",
                    aPriceNew, aPriceOld, aDemandQuantityNew, aDemandQuantityOld));
        }
        return calculateChangeInDemand(aDemandQuantityNew, aDemandQuantityOld)
                / calculateChangeInPrice(aPriceNew, aPriceOld);
    }

    double calculateChangeInDemand(final long aDemandQuantityNew, final long aDemandQuantityOld) {
        return (double) (aDemandQuantityNew - aDemandQuantityOld) / aDemandQuantityOld;
    }

    double calculateChangeInPrice(final BigDecimal aPriceNew, final BigDecimal aPriceOld) {
        return aPriceNew.subtract(aPriceOld)
                .divide(aPriceOld, CHANGE_IN_PRICE_SCALE, RoundingMode.CEILING).doubleValue();
    }

    private boolean isBigDecimalValid(final BigDecimal aBigDecimal) {
        return aBigDecimal != null && aBigDecimal.compareTo(BigDecimal.ZERO) > 0;
    }
}
