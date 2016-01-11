package ru.unn.agile.IntegrationMethods.Model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntegratorTest {
    private final double delta = 0.001;
    private Integrator integrator;
    private double analyticalIntegralValue;
    private IFunction function;

    @Before
    public void setUp() {
        function = new CosFunction();
        integrator = new Integrator(0.0, 1.0, function);
        analyticalIntegralValue = Math.sin(1.0);
    }

    @Test
    public void canCreateIntegrator() {
        assertNotNull(integrator);
    }

    @Test
    public void canSetLowerLimit() {
        assertEquals(0.0, integrator.getLowerLimit(), delta);
    }

    @Test
    public void canSetUpperLimit() {
        assertEquals(1.0, integrator.getUpperLimit(), delta);
    }

    @Test
    public void canSetStep() {
        double correctStep = 0.0001;

        assertEquals(correctStep, integrator.getStep(), delta);
    }

    @Test
    public void canUseLeftRectangles() {
        assertEquals(analyticalIntegralValue, integrator.leftRectangles(), delta);
    }

    @Test
    public void canUseRightRectangles() {
        assertEquals(analyticalIntegralValue, integrator.rightRectangles(), delta);
    }

    @Test
    public void canUseMidpointRectangles() {
        assertEquals(analyticalIntegralValue, integrator.midpointRectangles(), delta);
    }

    @Test
    public void canUseTrapezes() {
        assertEquals(analyticalIntegralValue, integrator.trapezes(), delta);
    }

    @Test
    public void canUseSimpson() {
        assertEquals(analyticalIntegralValue, integrator.simpson(), delta);
    }

    @Test
    public void canDetermineTheMostAccurateMethod() {
        double[] accuracy = new double[5];
        accuracy[0] = Math.abs(integrator.leftRectangles() - analyticalIntegralValue);
        accuracy[1] = Math.abs(integrator.rightRectangles() - analyticalIntegralValue);
        accuracy[2] = Math.abs(integrator.midpointRectangles() - analyticalIntegralValue);
        accuracy[3] = Math.abs(integrator.trapezes() - analyticalIntegralValue);
        accuracy[4] = Math.abs(integrator.simpson() - analyticalIntegralValue);

        int minIndex = 0;
        double minAccuracy = accuracy[0];
        for (int i = 1; i < 5; i++) {
            if (accuracy[i] < minAccuracy) {
                minAccuracy = accuracy[i];
                minIndex = i;
            }
        }

        assertEquals(4, minIndex);
    }
}

