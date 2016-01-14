package ru.unn.agile.Triangle;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.Triangle.Model.TriangleExceptions;
import ru.unn.agile.TriangleViewModel.Status;
import ru.unn.agile.TriangleViewModel.TriangleViewModel;
import ru.unn.agile.TriangleViewModel.ValuesToCalculate;

import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.containsString;

public class TriangleViewModelTests {
    private TriangleViewModel viewModel;
    @Before
    public void setUp() {
        FakeLogger fakeLogger = new FakeLogger();
        viewModel = new TriangleViewModel(fakeLogger);
    }

    private void setExampleValues() {
        viewModel.setCoordinate1X("9.65");
        viewModel.setCoordinate1Y("5");
        viewModel.setCoordinate1Z("-2.0");
        viewModel.setCoordinate2X("3");
        viewModel.setCoordinate2Y("10");
        viewModel.setCoordinate2Z("-1");
        viewModel.setCoordinate3X("-0.5");
        viewModel.setCoordinate3Y("1.5");
        viewModel.setCoordinate3Z("1.5");
        viewModel.setValueToCalculate(ValuesToCalculate.PERIMETER);
    }

    private String makePattern(final String value) {
        return "^.*coordinate [XYZ] of point [123] : " + value + ".*$";
    }

    @Test
    public void byDefaultCoordinate1XisEmptyString() {
        assertEquals(viewModel.getCoordinate1X(), "");
    }

    @Test
    public void byDefaultCoordinate1YisEmptyString() {
        assertEquals(viewModel.getCoordinate1Y(), "");
    }

    @Test
    public void byDefaultCoordinate1ZisEmptyString() {
        assertEquals(viewModel.getCoordinate1Z(), "");
    }

    @Test
    public void byDefaultCoordinate2XisEmptyString() {
        assertEquals(viewModel.getCoordinate2X(), "");
    }

    @Test
    public void byDefaultCoordinate2YisEmptyString() {
        assertEquals(viewModel.getCoordinate2Y(), "");
    }

    @Test
    public void byDefaultCoordinate2ZisEmptyString() {
        assertEquals(viewModel.getCoordinate2Z(), "");
    }

    @Test
    public void byDefaultCoordinate3XisEmptyString() {
        assertEquals(viewModel.getCoordinate3X(), "");
    }

    @Test
    public void byDefaultCoordinate3YisEmptyString() {
        assertEquals(viewModel.getCoordinate3Y(), "");
    }

    @Test
    public void byDefaultCoordinate3ZisEmptyString() {
        assertEquals(viewModel.getCoordinate3Z(), "");
    }

    @Test
    public void byDefaultResultIsZero() {
        assertEquals(viewModel.getResult(), "0.0");
    }

    @Test
    public void byDefaultValueToCalculateIsMEDIANS() {
        assertEquals(viewModel.getValueToCalculate(), ValuesToCalculate.MEDIANS);
    }

    @Test
    public void byDefaultStatusIsWaiting() {
        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void byDefaultCalculateButtonIsDisabled() {
        assertFalse(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void enterExampleValuesIsFinishedWithRightStatus() throws Exception {
        setExampleValues();
        viewModel.checkInputAndChangeStateIfOK();
        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void computeExampleIsFinishedWithRightStatus() throws Exception {
        setExampleValues();
        viewModel.compute();
        assertEquals(Status.SUCCESS, viewModel.getStatus());
    }

    @Test
    public void whenAllFieldsAreCompletedCorrectlyButtonIsEnabled() {
        setExampleValues();
        viewModel.checkInputAndChangeStateIfOK();
        assertTrue(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void whenEnterStringStatusIsBadFormat() {
        viewModel.setCoordinate1X("Ooo");
        viewModel.checkInputAndChangeStateIfOK();
        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void whenTriangleIsDegenerateStatusIsCorrect() throws Exception {
        viewModel.setCoordinate1X("1");
        viewModel.setCoordinate1Y("1");
        viewModel.setCoordinate1Z("1");
        viewModel.setCoordinate2X("2");
        viewModel.setCoordinate2Y("2");
        viewModel.setCoordinate2Z("2");
        viewModel.setCoordinate3X("3");
        viewModel.setCoordinate3Y("3");
        viewModel.setCoordinate3Z("3");
        viewModel.setValueToCalculate(ValuesToCalculate.PERIMETER);
        viewModel.compute();
        assertEquals(viewModel.getStatus(), TriangleExceptions.DEGENERATE_TRIANGLE.toString());
    }

    @Test
    public void buttonIsDisabledWhenNotFieldsAreFilled() {
        viewModel.setCoordinate1X("1");
        assertEquals(viewModel.isCalculateButtonEnabled(), false);
    }

    @Test
    public void viewModelWithLoggerCanBeCreated() {
        FakeLogger fakeLogger = new FakeLogger();
        TriangleViewModel viewModelWithFakeLogger = new TriangleViewModel(fakeLogger);
        assertNotNull(viewModelWithFakeLogger);
    }

    @Test(expected = IllegalArgumentException.class)
    public void viewModelThrowsExceptionWhenLoggerIsNull() {
        new TriangleViewModel(null);
    }

    @Test
    public void byDefaultLoggerIsEmpty() {
        List<String> log = viewModel.getLog();
        assertEquals(true, log.isEmpty());
    }

    @Test
    public void loggerIncludesCoordinate1X() throws Exception {
        setExampleValues();
        List<String> log = viewModel.getLog();
        String logString = log.toString();
        String pattern = makePattern(viewModel.getCoordinate1X().toString());
        assertTrue(Pattern.matches(pattern, logString));
    }

    @Test
    public void loggerIncludesCoordinate1Y() throws Exception {
        setExampleValues();
        List<String> log = viewModel.getLog();
        String logString = log.toString();
        String pattern = makePattern(viewModel.getCoordinate1Y().toString());
        assertTrue(Pattern.matches(pattern, logString));
    }

    @Test
    public void loggerIncludesCoordinate1Z() throws Exception {
        setExampleValues();
        List<String> log = viewModel.getLog();
        String logString = log.toString();
        String pattern = makePattern(viewModel.getCoordinate1Z().toString());
        assertTrue(Pattern.matches(pattern, logString));
    }

    @Test
    public void loggerIncludesCoordinate2X() throws Exception {
        setExampleValues();
        List<String> log = viewModel.getLog();
        String logString = log.toString();
        String pattern = makePattern(viewModel.getCoordinate2X().toString());
        assertTrue(Pattern.matches(pattern, logString));
    }

    @Test
    public void loggerIncludesCoordinate2Y() throws Exception {
        setExampleValues();
        List<String> log = viewModel.getLog();
        String logString = log.toString();
        String pattern = makePattern(viewModel.getCoordinate2Y().toString());
        assertTrue(Pattern.matches(pattern, logString));
    }

    @Test
    public void loggerIncludesCoordinate2Z() throws Exception {
        setExampleValues();
        List<String> log = viewModel.getLog();
        String logString = log.toString();
        String pattern = makePattern(viewModel.getCoordinate2Z().toString());
        assertTrue(Pattern.matches(pattern, logString));
    }

    @Test
    public void loggerIncludesCoordinate3X() throws Exception {
        setExampleValues();
        List<String> log = viewModel.getLog();
        String logString = log.toString();
        String pattern = makePattern(viewModel.getCoordinate3X().toString());
        assertTrue(Pattern.matches(pattern, logString));
    }

    @Test
    public void loggerIncludesCoordinate3Y() throws Exception {
        setExampleValues();
        List<String> log = viewModel.getLog();
        String logString = log.toString();
        String pattern = makePattern(viewModel.getCoordinate3Y().toString());
        assertTrue(Pattern.matches(pattern, logString));
    }

    @Test
    public void loggerIncludesCoordinate3Z() throws Exception {
        setExampleValues();
        List<String> log = viewModel.getLog();
        String logString = log.toString();
        String pattern = makePattern(viewModel.getCoordinate3Z().toString());
        assertTrue(Pattern.matches(pattern, logString));
    }

    @Test
    public void loggerIncludesValueToCalculate() throws Exception {
        setExampleValues();
        List<String> log = viewModel.getLog();
        String logString = log.toString();
        boolean doesContainTemplateValue =
                logString.contains(viewModel.getValueToCalculate().toString());
        boolean doesContainTemplateField = logString.contains("value to calculate");
        assertTrue(doesContainTemplateField && doesContainTemplateValue);
    }

    @Test
    public void recordIsCorrectWhenCoordinate1XChanged() {
        setExampleValues();
        viewModel.setCoordinate1X("1.25");
        String rightRecord = "New value was chosen for "
                + "coordinate X of point 1 : " + viewModel.getCoordinate1X();
        String record = viewModel.getLog().toString();
        assertThat(record, containsString(rightRecord));
    }

    @Test
    public void noRecordWhenCoordinate1XIsTheSame() {
        viewModel.setCoordinate1X("");
        List<String> log = viewModel.getLog();
        assertEquals(0, log.size());
    }

    @Test
    public void recordIsCorrectWhenCoordinate1YChanged() {
        setExampleValues();
        String rightRecord = "New value was chosen for "
                + "coordinate Y of point 1 : " + viewModel.getCoordinate1Y();
        String record = viewModel.getLog().toString();
        assertThat(record, containsString(rightRecord));
    }

    @Test
    public void noRecordWhenCoordinate1YIsTheSame() {
        viewModel.setCoordinate1Y("");
        List<String> log = viewModel.getLog();
        assertEquals(0, log.size());
    }

    @Test
    public void recordIsCorrectWhenCoordinate1ZChanged() {
        setExampleValues();
        String rightRecord = "New value was chosen for "
                + "coordinate Z of point 1 : " + viewModel.getCoordinate1Z();
        String record = viewModel.getLog().toString();
        assertThat(record, containsString(rightRecord));
    }

    @Test
    public void noRecordWhenCoordinate1ZIsTheSame() {
        viewModel.setCoordinate1Z("");
        List<String> log = viewModel.getLog();
        assertEquals(0, log.size());
    }

    @Test
    public void recordIsCorrectWhenCoordinate2XChanged() {
        setExampleValues();
        String rightRecord = "New value was chosen for "
                + "coordinate X of point 2 : " + viewModel.getCoordinate2X();
        String record = viewModel.getLog().toString();
        assertThat(record, containsString(rightRecord));
    }

    @Test
    public void noRecordWhenCoordinate2XIsTheSame() {
        viewModel.setCoordinate2X("");
        List<String> log = viewModel.getLog();
        assertEquals(0, log.size());
    }

    @Test
    public void recordIsCorrectWhenCoordinate2YChanged() {
        setExampleValues();
        String rightRecord = "New value was chosen for "
                + "coordinate Y of point 2 : " + viewModel.getCoordinate2Y();
        String record = viewModel.getLog().toString();
        assertThat(record, containsString(rightRecord));
    }

    @Test
    public void noRecordWhenCoordinate2YIsTheSame() {
        viewModel.setCoordinate2Y("");
        List<String> log = viewModel.getLog();
        assertEquals(0, log.size());
    }

    @Test
    public void recordIsCorrectWhenCoordinate2ZChanged() {
        setExampleValues();
        String rightRecord = "New value was chosen for "
                + "coordinate Z of point 2 : " + viewModel.getCoordinate2Z();
        String record = viewModel.getLog().toString();
        assertThat(record, containsString(rightRecord));
    }

    @Test
    public void noRecordWhenCoordinate2ZIsTheSame() {
        viewModel.setCoordinate2Z("");
        List<String> log = viewModel.getLog();
        assertEquals(0, log.size());
    }

    @Test
         public void recordIsCorrectWhenCoordinate3XChanged() {
        setExampleValues();
        String rightRecord = "New value was chosen for "
                + "coordinate X of point 3 : " + viewModel.getCoordinate3X();
        String record = viewModel.getLog().toString();
        assertThat(record, containsString(rightRecord));
    }

    @Test
    public void noRecordWhenCoordinate3XIsTheSame() {
        viewModel.setCoordinate3X("");
        List<String> log = viewModel.getLog();
        assertEquals(0, log.size());
    }

    @Test
    public void recordIsCorrectWhenCoordinate3YChanged() {
        setExampleValues();
        String rightRecord = "New value was chosen for "
                + "coordinate Y of point 3 : " + viewModel.getCoordinate3Y();
        String record = viewModel.getLog().toString();
        assertThat(record, containsString(rightRecord));
    }

    @Test
    public void noRecordWhenCoordinate3YIsTheSame() {
        viewModel.setCoordinate3Y("");
        List<String> log = viewModel.getLog();
        assertEquals(0, log.size());
    }

    @Test
    public void recordIsCorrectWhenCoordinate3ZChanged() {
        setExampleValues();
        String rightRecord = "New value was chosen for "
                + "coordinate Z of point 3 : " + viewModel.getCoordinate3Z();
        String record = viewModel.getLog().toString();
        assertThat(record, containsString(rightRecord));
    }

    @Test
    public void noRecordWhenCoordinate3ZIsTheSame() {
        viewModel.setCoordinate3Z("");
        List<String> log = viewModel.getLog();
        assertEquals(0, log.size());
    }

    @Test
    public void recordIsCorrectWhenValueToCalculateChanged() {
        setExampleValues();
        String rightRecord = "New value was chosen for "
                + "value to calculate : " + viewModel.getValueToCalculate();
        String record = viewModel.getLog().toString();
        assertThat(record, containsString(rightRecord));
    }

    @Test
    public void noRecordWhenValueToCalculateIsTheSame() {
        viewModel.setValueToCalculate(ValuesToCalculate.MEDIANS);
        List<String> log = viewModel.getLog();
        assertEquals(0, log.size());
    }

    @Test
    public void recordIsCorrectWhenCalculate() throws Exception {
        setExampleValues();
        viewModel.compute();
        List<String> log = viewModel.getLog();
        assertTrue(log.size() > 10);
    }

    @Test
    public void numberOfRecordsIsCorrect() throws Exception {
        setExampleValues();
        List<String> log = viewModel.getLog();
        assertTrue(log.size() == 10);
    }

    public void setViewModel(final TriangleViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
