package ru.unn.agile.ElasticityOfDemand;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by imozerov on 12.01.16.
 */
public class ElasticityOfDemandCalculatorTest {

    @Test
    public void testCalculate_shouldReturnValidNumber() throws Exception {
        ElasticityOfDemandCalculator calculator = new ElasticityOfDemandCalculator();
        assertEquals(0.111, calculator.calculate(new BigDecimal(10), new BigDecimal(9), 150, 110), 0.01);
    }

    @Test
    public void testCalculate_sameNumbers_shouldZero() throws Exception {
        ElasticityOfDemandCalculator calculator = new ElasticityOfDemandCalculator();
        assertEquals(0, calculator.calculate(new BigDecimal(10), new BigDecimal(10), 150, 150), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculate_shouldThrowOnInvalidInput() throws Exception {
        ElasticityOfDemandCalculator calculator = new ElasticityOfDemandCalculator();
        calculator.calculate(new BigDecimal(0), new BigDecimal(9), 150, 0);
    }

    @Test
    public void testCalculateChangeInDemand() throws Exception {
        ElasticityOfDemandCalculator calculator = new ElasticityOfDemandCalculator();
        assertEquals(0.5, calculator.calculateChangeInDemand(1, 2), 0);
    }

    @Test
    public void testCalculateChangeInPrice() throws Exception {
        ElasticityOfDemandCalculator calculator = new ElasticityOfDemandCalculator();
        assertEquals(0.5, calculator.calculateChangeInPrice(new BigDecimal(1), new BigDecimal(2)), 0);
    }
}