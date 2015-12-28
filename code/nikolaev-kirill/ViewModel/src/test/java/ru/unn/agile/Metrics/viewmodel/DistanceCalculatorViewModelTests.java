package ru.unn.agile.Metrics.viewmodel;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DistanceCalculatorViewModelTests {

    private DistanceCalculatorViewModel viewModel;

    public void setViewModel(final DistanceCalculatorViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUp() {
        viewModel = new DistanceCalculatorViewModel(new FakeLogger());
    }

    @Test
    public void calculateButtonIsDisabledByDefault() {
        assertTrue(viewModel.isCalculateButtonDisabled());
    }

    @Test
    public void calculateButtonIsDisabledWhenBadInputFormat() {
        viewModel.setFirstVector("1 -2.0 3");
        viewModel.setSecondVector("@trash 1.0$");

        assertTrue(viewModel.isCalculateButtonDisabled());
    }

    @Test
    public void calculateButtonIsDisabledWhenIncompleteInput() {
        viewModel.setFirstVector("");
        viewModel.setSecondVector("1 -2.0 3");

        assertTrue(viewModel.isCalculateButtonDisabled());
    }

    @Test
    public void calculateButtonIsDisabledWhenDifferentSize() {
        viewModel.setFirstVector("4.0 5.0");
        viewModel.setSecondVector("1 -2.0 3");

        assertTrue(viewModel.isCalculateButtonDisabled());
    }

    @Test
    public void calculateButtonIsEnabledWhenCorrectInput() {
        viewModel.setFirstVector("1 -2.0 3");
        viewModel.setSecondVector("-4.0 5 -6.0");

        assertFalse(viewModel.isCalculateButtonDisabled());
    }

    @Test
    public void calculateButtonIsDisabledWhenClearVector() {
        viewModel.setFirstVector("1 -2.0 3");
        viewModel.setSecondVector("-4.0 5 -6.0");
        viewModel.setSecondVector("");

        assertTrue(viewModel.isCalculateButtonDisabled());
    }

    @Test
    public void calculateButtonIsDisabledWhenClearVectorComponent() {
        viewModel.setFirstVector("1 -2.0 3");
        viewModel.setSecondVector("-4.0 5 -6.0");
        viewModel.setFirstVector("1 -2.0");

        assertTrue(viewModel.isCalculateButtonDisabled());
    }

    @Test
    public void calculateButtonIsEnabledWhenEditVectorFormat() {
        viewModel.setFirstVector("1.@0 -2.0$  ");
        viewModel.setSecondVector("-3.0 4.0");
        viewModel.setFirstVector("1.0 -2.0");

        assertFalse(viewModel.isCalculateButtonDisabled());
    }

    @Test
    public void calculateButtonIsEnabledWhenAddMissingVectorComponent() {
        viewModel.setFirstVector("1 -2.0 3");
        viewModel.setSecondVector("5 -6.0");
        viewModel.setSecondVector("-4.0 5 -6.0");

        assertFalse(viewModel.isCalculateButtonDisabled());
    }

    @Test
    public void showHelpMessageByDefault() {
        assertEquals(viewModel.HELP_MESSAGE, viewModel.getInputStatus());
    }

    @Test
    public void showErrorMessageWhenBadInputFormat() {
        viewModel.setFirstVector("1 -2.0 3");
        viewModel.setSecondVector("@trash 1.0$");

        assertEquals("Bad vector format", viewModel.getInputStatus());
    }

    @Test
    public void showErrorMessageWhenDifferentSize() {
        viewModel.setFirstVector("4.0 5.0");
        viewModel.setSecondVector("1 -2.0 3");

        assertEquals("Vectors have different size", viewModel.getInputStatus());
    }

    @Test
    public void noMessageWhenCorrectInput() {
        viewModel.setFirstVector("1 -2.0 3");
        viewModel.setSecondVector("-4.0 5 -6.0");

        assertEquals("", viewModel.getInputStatus());
    }

    @Test
    public void showHelpMessageWhenClearVector() {
        viewModel.setFirstVector("1 -2.0 3");
        viewModel.setSecondVector("-4.0 5 -6.0");
        viewModel.setSecondVector("");

        assertEquals(viewModel.HELP_MESSAGE, viewModel.getInputStatus());
    }

    @Test
    public void showErrorMessageWhenClearVectorComponent() {
        viewModel.setFirstVector("1 -2.0 3");
        viewModel.setSecondVector("-4.0 5 -6.0");
        viewModel.setFirstVector("1 -2.0");

        assertEquals("Vectors have different size", viewModel.getInputStatus());
    }

    @Test
    public void hideErrorMessageWhenEditVectorFormat() {
        viewModel.setFirstVector("1.@0 -2.0$  ");
        viewModel.setSecondVector("-3.0 4.0");
        viewModel.setFirstVector("1.0 -2.0");

        assertEquals("", viewModel.getInputStatus());
    }

    @Test
    public void hideErrorMessageWhenAddMissingVectorComponent() {
        viewModel.setFirstVector("1 -2.0 3");
        viewModel.setSecondVector("5 -6.0");
        viewModel.setSecondVector("-4.0 5 -6.0");

        assertEquals("", viewModel.getInputStatus());
    }

    @Test
    public void showErrorMessageWhenEmptyAndBadInputFormat() {
        viewModel.setFirstVector("@trash 1.0$");
        viewModel.setSecondVector("");

        assertEquals("Bad vector format", viewModel.getInputStatus());
    }

    @Test
    public void showHelpMessageWhenIncompleteInput() {
        viewModel.setFirstVector("1 -2.0 3");
        viewModel.setSecondVector("");

        assertEquals(viewModel.HELP_MESSAGE, viewModel.getInputStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateThrowsWhenInvalidMetricName() {
        viewModel.setFirstVector("1.0 -2.0 3.0");
        viewModel.setSecondVector("-4.0 5.0 -6.0");
        viewModel.setMetric("RHO ZERO");

        viewModel.calculate();
    }

    @Test(expected = IllegalStateException.class)
    public void calculateThrowsWhenIncompleteInput() {
        viewModel.setFirstVector("");
        viewModel.setSecondVector("1 -2.0 3");
        viewModel.setMetric("RHO ONE");

        viewModel.calculate();
    }

    @Test(expected = IllegalStateException.class)
    public void calculateThrowsWhenDifferentSize() {
        viewModel.setFirstVector("4.0 5.0");
        viewModel.setSecondVector("1 -2.0 3");
        viewModel.setMetric("RHO TWO");

        viewModel.calculate();
    }

    @Test(expected = IllegalStateException.class)
    public void calculateThrowsWhenBadInputFormat() {
        viewModel.setFirstVector("1 -2.0 3");
        viewModel.setSecondVector("@trash 1.0$");
        viewModel.setMetric("RHO THREE");

        viewModel.calculate();
    }

    @Test(expected = IllegalStateException.class)
    public void calculateThrowsWhenNoInput() {
        viewModel.setFirstVector("");
        viewModel.setSecondVector("");
        viewModel.setMetric("RHO FOUR");

        viewModel.calculate();
    }

    @Test
    public void whenCalculateDistanceInRhoInf() {
        viewModel.setFirstVector("4.1 -5.2 6.3 -7.4");
        viewModel.setSecondVector("3.1 -9.2 8.3 -2.4");
        viewModel.setMetric("RHO INF");

        viewModel.calculate();

        assertEquals("5.0", viewModel.getResult());
    }

    @Test
    public void whenCalculateDistanceInRhoOne() {
        viewModel.setFirstVector("4.1 -5.2 6.3 -7.4");
        viewModel.setSecondVector("3.1 -9.2 8.3 -2.4");
        viewModel.setMetric("RHO ONE");

        viewModel.calculate();

        assertEquals("12.0", viewModel.getResult());
    }

    @Test
    public void whenCalculateDistanceInRhoTwo() {
        viewModel.setFirstVector("-4.1 6.3 -7.4 0.5");
        viewModel.setSecondVector("-3.1 9.3 -2.4 1.5");
        viewModel.setMetric("RHO TWO");

        viewModel.calculate();

        assertEquals("6.0", viewModel.getResult());
    }

    @Test
    public void whenCalculateDistanceInRhoThree() {
        viewModel.setFirstVector("-4.1 6.3 -7.4 5.5 -1.6");
        viewModel.setSecondVector("-3.1 8.3 -4.4 2.5 -0.6");
        viewModel.setMetric("RHO THREE");

        viewModel.calculate();

        assertEquals("4.0", viewModel.getResult());
    }

    @Test
    public void whenCalculateDistanceInRhoFour() {
        viewModel.setFirstVector("-4.1 6.3 -7.4 9.5 -1.6 0.7");
        viewModel.setSecondVector("-5.1 4.3 -5.4 7.5 -3.6 2.7");
        viewModel.setMetric("RHO FOUR");

        viewModel.calculate();

        assertEquals("3.0", viewModel.getResult());
    }

    @Test
    public void logIsEmptyByDefault() {
        List<String> log = viewModel.getLog();
        assertTrue(log.isEmpty());
    }

    @Test
    public void logContainsMessageAfterCalculation() {
        setInputData();

        viewModel.calculate();

        String message = viewModel.getLastLogMessage();
        assertTrue(message.matches(".*" + viewModel.CALCULATE_PRESSED + ".*"));
    }

    @Test
    public void logContainsMetricAfterCalculation() {
        setInputData();

        viewModel.calculate();

        String message = viewModel.getLastLogMessage();
        assertTrue(message.matches(".*" + "Metric: " + viewModel.getMetricName() + ".*"));
    }

    @Test
    public void logContainsInputVectorsInProperFormatAfterCalculation() {
        setInputData();

        viewModel.calculate();

        String message = viewModel.getLastLogMessage();
        assertTrue(message.matches(".*" + "Arguments: \\[" + viewModel.firstVectorProperty().get()
                + "\\]; \\[" + viewModel.secondVectorProperty().get() + "\\].*"));
    }

    @Test
    public void canSeeMetricChangeInLog() {
        viewModel.onMetricChange("RHO INF", "RHO ONE");

        String message = viewModel.getLastLogMessage();
        assertTrue(message.matches(".*" + viewModel.METRIC_CHANGED + "RHO ONE" + ".*"));
    }

    @Test
    public void metricNotLoggedIfNotChanged() {
        viewModel.onMetricChange("RHO INF", "RHO INF");

        List<String> log = viewModel.getLog();
        assertTrue(log.isEmpty());
    }

    @Test
    public void inputArgumentsAreProperlyLogged() {
        setInputData();

        viewModel.onFocusChange(Boolean.TRUE, Boolean.FALSE);

        String message = viewModel.getLastLogMessage();
        assertTrue(message.matches(".*" + viewModel.INPUT_UPDATED + " Arguments: \\["
                + viewModel.firstVectorProperty().get() + "\\]; \\["
                + viewModel.secondVectorProperty().get() + "\\].*"));
    }

    @Test
    public void noLogMessageWhenGainFocus() {
        viewModel.setFirstVector("4.0 5.0");

        viewModel.onFocusChange(Boolean.FALSE, Boolean.TRUE);

        List<String> log = viewModel.getLog();
        assertTrue(log.isEmpty());
    }

    @Test
    public void sameVectorNotLoggedTwice() {
        viewModel.setFirstVector("4.0 5.0");

        viewModel.onFocusChange(Boolean.TRUE, Boolean.FALSE);
        viewModel.onFocusChange(Boolean.TRUE, Boolean.FALSE);

        List<String> log = viewModel.getLog();
        assertTrue(log.size() == 1);
    }

    @Test
    public void canPutSeveralDifferentMessagesInLog() {
        viewModel.setFirstVector("4.1 -5.2 6.3 -7.4");
        viewModel.onFocusChange(Boolean.TRUE, Boolean.FALSE);
        viewModel.setSecondVector("3.1 -9.2 8.3 -2.4");
        viewModel.onFocusChange(Boolean.TRUE, Boolean.FALSE);
        viewModel.onMetricChange("RHO INF", "RHO TWO");

        viewModel.calculate();

        List<String> log = viewModel.getLog();
        assertEquals(4, log.size());
    }

    private void setInputData() {
        viewModel.setFirstVector("-4.1 6.3 -7.4 9.5 -1.6 0.7");
        viewModel.setSecondVector("-5.1 4.3 -5.4 7.5 -3.6 2.7");
        viewModel.setMetric("RHO FOUR");
    }
}
