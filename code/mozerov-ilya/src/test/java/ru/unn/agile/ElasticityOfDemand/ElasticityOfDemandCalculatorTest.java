package ru.unn.agile.ElasticityOfDemand;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by imozerov on 12.01.16.
 */
public class ElasticityOfDemandCalculatorTest {

    @Test
    public void calculateShouldReturnValidNumber() throws Exception {
        ElasticityOfDemandCalculator calculator = new ElasticityOfDemandCalculator();

        double result = calculator.calculate(BigDecimal.TEN, new BigDecimal(9), 110, 150);

        assertEquals(-2.39, result, 0.01);
    }

    @Test
    public void calculateSameNumbersShouldReturnNan() throws Exception {
        ElasticityOfDemandCalculator calculator = new ElasticityOfDemandCalculator();

        double result = calculator.calculate(BigDecimal.TEN, BigDecimal.TEN, 150, 150);

        assertTrue(Double.isNaN(result));
    }

    @Test
    public void calculateSamePriceAndDemandWasSmallerShouldReturnNegativeInfinity()
            throws Exception {
        ElasticityOfDemandCalculator calculator = new ElasticityOfDemandCalculator();

        double result = calculator.calculate(BigDecimal.TEN, BigDecimal.TEN, 110, 150);

        assertTrue(Double.isInfinite(result));
        assertEquals(Double.NEGATIVE_INFINITY, result, 0.0001);
    }

    @Test
    public void calculateSamePriceAndDemandWasBiggerShouldReturnNegativeInfinity()
            throws Exception {
        ElasticityOfDemandCalculator calculator = new ElasticityOfDemandCalculator();

        double result = calculator.calculate(BigDecimal.TEN, BigDecimal.TEN, 150, 110);

        assertTrue(Double.isInfinite(result));
        assertEquals(Double.POSITIVE_INFINITY, result, 0.0001);
    }

    @Test
    public void calculateSameDemandShouldReturnZero() throws Exception {
        ElasticityOfDemandCalculator calculator = new ElasticityOfDemandCalculator();

        double result = calculator.calculate(new BigDecimal(14), BigDecimal.TEN, 150, 150);

        assertEquals(0, result, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateZeroPriceShouldThrow() throws Exception {
        ElasticityOfDemandCalculator calculator = new ElasticityOfDemandCalculator();
        calculator.calculate(BigDecimal.ZERO, new BigDecimal(9), 150, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateZeroDemandShouldThrow() throws Exception {
        ElasticityOfDemandCalculator calculator = new ElasticityOfDemandCalculator();
        calculator.calculate(new BigDecimal(16), new BigDecimal(9), 150, 0);
    }

    @Test
    public void calculateChangeInDemandDemandWasBiggerShouldReturnNegativeValue()
            throws Exception {
        ElasticityOfDemandCalculator calculator = new ElasticityOfDemandCalculator();

        double result = calculator.calculateChangeInDemand(1L, 2L);

        assertEquals(-0.5, result, 0);
    }

    @Test
    public void calculateChangeInDemandDemandWasSmallerShouldReturnPositiveValue()
            throws Exception {
        ElasticityOfDemandCalculator calculator = new ElasticityOfDemandCalculator();

        double result = calculator.calculateChangeInDemand(3L, 2L);

        assertEquals(0.5, result, 0);
    }

    @Test
    public void calculateChangeInDemandDemandWasSmallerIntValuesShouldReturnPositiveValue()
            throws Exception {
        ElasticityOfDemandCalculator calculator = new ElasticityOfDemandCalculator();

        double result = calculator.calculateChangeInDemand(3, 2);

        assertEquals(0.5, result, 0);
    }

    @Test
    public void calculateChangeInDemandSameNumbersShouldReturnZero() throws Exception {
        ElasticityOfDemandCalculator calculator = new ElasticityOfDemandCalculator();

        double result = calculator.calculateChangeInDemand(2L, 2L);

        assertEquals(0, result, 0);
    }

    @Test
    public void calculateChangeInPricePriceWasBiggerShouldReturnNegativeValue() throws Exception {
        ElasticityOfDemandCalculator calculator = new ElasticityOfDemandCalculator();

        double result = calculator.calculateChangeInPrice(BigDecimal.ONE, new BigDecimal(2));

        assertEquals(-0.5, result, 0);
    }

    @Test
    public void calculateChangeInPricePriceWasSmallerShouldReturnNegativeValue() throws Exception {
        ElasticityOfDemandCalculator calculator = new ElasticityOfDemandCalculator();

        double result = calculator.calculateChangeInPrice(new BigDecimal(3), new BigDecimal(2));

        assertEquals(0.5, result, 0);
    }

    @Test
    public void calculateChangeInPriceSameNumbersShouldReturnZero() throws Exception {
        ElasticityOfDemandCalculator calculator = new ElasticityOfDemandCalculator();

        double result = calculator.calculateChangeInPrice(BigDecimal.ONE, BigDecimal.ONE);

        assertEquals(0, result, 0);
    }
}
