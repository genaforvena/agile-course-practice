package ru.unn.agile.ElasticityOfDemand;

import java.math.BigDecimal;

/**
 * Created by imozerov on 12.01.16.
 */
public class ElasticityOfDemandCalculator {
    public double calculate(BigDecimal aPriceNew, BigDecimal aPriceOld,
                      long aDemandQuantityNew, long aDemandQuantityOld) {
        return calculateChangeInDemand(aDemandQuantityNew, aDemandQuantityOld)
                / calculateChangeInPrice(aPriceNew, aPriceOld);
    }

    double calculateChangeInDemand(long aDemandQuantityNew, long aDemandQuantityOld) {
        return 0;
    }

    double calculateChangeInPrice(BigDecimal aPriceNew, BigDecimal aPriceOld) {
        return 0;
    }
}
